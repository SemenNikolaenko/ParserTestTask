import configuration.SpringConfiguration;
import converter.ConvertToOutputFormat;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import output.OutputObject;
import parser.CustomParser;
import parser.factory.AbstractFactory;
import parser.factory.AllParserFactory;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class Application {

    public Application() {
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        CopyOnWriteArrayList<OutputObject> readyObject = new CopyOnWriteArrayList<>();
        Lock readyListLock = new ReentrantLock();

        BlockingQueue<String> bufferList = new ArrayBlockingQueue<String>(100);
        Lock bufferListLock = new ReentrantLock();

        Semaphore semaphoreRead = new Semaphore(2);
        Semaphore semaphoreWrite = new Semaphore(2);


        BlockingQueue<String> inputFilesRequiredConvert = new ArrayBlockingQueue<String>(args.length);
        Arrays.stream(args).forEach(inputFilesRequiredConvert::add);
        Lock inputFileLock = new ReentrantLock();

        ConvertToOutputFormat converter = context.getBean(ConvertToOutputFormat.class);
        Thread thread = null;
        Thread thread1 = null;
        while (!inputFilesRequiredConvert.isEmpty()) {
            semaphoreRead.acquire();
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    AbstractFactory factory;
                    CustomParser parser;
                    try {
                        if (bufferListLock.tryLock(100, TimeUnit.MILLISECONDS))inputFileLock.tryLock(100, TimeUnit.MILLISECONDS);

                        String inputFile = inputFilesRequiredConvert.take();
                        factory = AllParserFactory.getFactory(inputFile);
                        parser = factory.createParser();
                        parser.setWorkParam(inputFile);
                        parser.getWorkResult().forEach(bufferList::add);

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        inputFileLock.unlock();
                        bufferListLock.unlock();
                        semaphoreRead.release();
                    }
                }
            });
            thread.start();
            thread.join();
        }
        while (!bufferList.isEmpty()) {
            semaphoreWrite.acquire();
            thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (bufferListLock.tryLock(100, TimeUnit.MILLISECONDS))readyListLock.tryLock(100, TimeUnit.MILLISECONDS);
                        readyObject.add(converter.getReadyObject(bufferList.take()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        bufferListLock.unlock();
                        readyListLock.unlock();
                        semaphoreWrite.release();
                    }
                }
            });
            thread1.start();
            thread1.join();
        }

        readyObject.forEach(System.out::println);

    }
}

