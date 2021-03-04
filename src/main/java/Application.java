import configuration.SpringConfiguration;
import converter.ConvertToOutputFormat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import output.OutputObject;
import parser.AbstractFactory;
import parser.AllParserFactory;
import parser.CustomParser;


import javax.swing.text.html.parser.Parser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Application {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        String filenameJson = "C:\\Users\\QA\\Desktop\\test.json";
        String filenameCsv = "C:\\Users\\QA\\Desktop\\test.csv";


        AbstractFactory factory = AllParserFactory.getFactory(filenameJson, context);
        CustomParser customParser = factory.createParser();
        ConvertToOutputFormat convertToOutputFormat = context.getBean(ConvertToOutputFormat.class);
        List<OutputObject> a = convertToOutputFormat.getReadyObject(customParser.getWorkResult());

        List<OutputObject> list = new ArrayList<>();
        for (OutputObject s : a) {
            list.add(s);
        }

        factory = AllParserFactory.getFactory(filenameCsv, context);
        customParser = factory.createParser();
       convertToOutputFormat= context.getBean(ConvertToOutputFormat.class);
        a = convertToOutputFormat.getReadyObject(customParser.getWorkResult());
        for (OutputObject s : a) {
            list.add(s);
        }
        list.forEach(System.out::println);

    }
}

