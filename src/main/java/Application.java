import configuration.SpringConfiguration;
import converter.ConvertToOutputFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import output.OutputObject;
import parser.factory.AllParserFactory;
import parser.implementation.CsvParser;
import parser.implementation.JsonParser;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
class Application {

    public Application() {
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);

//        ParseAndConvert parseAndConvert = context.getBean(ParseAndConvert.class);
//        AllParserFactory factories = context.getBean(AllParserFactory.class);
//        Semaphore semaphore = new Semaphore(4);
//        parseAndConvert.initClass(context, factories, args, semaphore);
//        parseAndConvert.parsing();
//        parseAndConvert.convert();
//        int countThread=Thread.getAllStackTraces().keySet().size();
//        List<OutputObject> list = parseAndConvert.returnWorkResult();
//        System.out.println(list.size());

        CopyOnWriteArrayList<OutputObject> readyObject = new CopyOnWriteArrayList<>();
        Lock readyListLock = new ReentrantLock();

        BlockingQueue<String> bufferList = new ArrayBlockingQueue<String>(100);
        Lock bufferListLock = new ReentrantLock();

        Semaphore semaphoreRead = new Semaphore(2);
        Semaphore semaphoreWrite = new Semaphore(2);


        BlockingQueue<String> inputFilesRequiredConvert = new ArrayBlockingQueue<String>(args.length);
        Arrays.stream(args).forEach(inputFilesRequiredConvert::add);
        Lock inputFileLock = new ReentrantLock();


        AllParserFactory factories = context.getBean(AllParserFactory.class);
        CsvParser csvParser = context.getBean(CsvParser.class);
        JsonParser jsonParser = context.getBean(JsonParser.class);
        ConvertToOutputFormat converter = context.getBean(ConvertToOutputFormat.class);

        while (!inputFilesRequiredConvert.isEmpty()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean locked = false;
                    boolean lockBuffer = false;
                    try {
                        locked = inputFileLock.tryLock();
                        lockBuffer = bufferListLock.tryLock();
                        semaphoreRead.acquire();
                        String s = inputFilesRequiredConvert.take();
                        if (locked && lockBuffer) {
                            if (s.endsWith(".json")) {
                                jsonParser.setWorkParam(s);
                                jsonParser.getWorkResult().forEach(bufferList::add);
                                semaphoreRead.release();
                            } else if (s.endsWith(".csv")) {
                                csvParser.setWorkParam(s);
                                csvParser.getWorkResult().forEach(bufferList::add);
                                semaphoreRead.release();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        inputFileLock.unlock();
                        bufferListLock.unlock();
                    }
                }

            }).start();
        }
        Thread.sleep(500);
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean bufferLock = false;
                boolean readyObjLock = false;
                try {
                    bufferLock=bufferListLock.tryLock();
                    readyObjLock=readyListLock.tryLock();
                    if (bufferLock&&readyObjLock) {
                        semaphoreWrite.acquire();
                        readyObject.add(converter.getReadyObject(bufferList.take()));
                        semaphoreWrite.release();

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    bufferListLock.unlock();
                    readyListLock.unlock();
            }
        }
    }).start();


//        AllParserFactory factories = context.getBean(AllParserFactory.class);
//        ConvertToOutputFormat convert = context.getBean(ConvertToOutputFormat.class);
//
//        AbstractFactory factory;
//
//        CustomParser parser;
//
//
//
//
//        for (String s:args) {
//            factory= factories.getFactory(s);
//            parser=factory.createParser();
//            parser.setWorkParam(s);
//            convert.getReadyObject(parser.getWorkResult()).forEach(list::add);
//        }
//
//
//        for (OutputObject obj:list
//             ) {
//            System.out.println(obj.toString());
//        }
//
//

}
}

