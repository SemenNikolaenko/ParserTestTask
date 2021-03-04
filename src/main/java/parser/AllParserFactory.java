package parser;

import org.springframework.context.ApplicationContext;

public class AllParserFactory {


   public static AbstractFactory getFactory(String filePath, ApplicationContext context) throws Exception {
        AbstractFactory abstractFactory=null;
        if (filePath.endsWith(".json")) abstractFactory= new JsonParserFactory(filePath, context);
        else if (filePath.endsWith("csv")) abstractFactory= new CsvParserFactory(filePath, context);
        if (abstractFactory!=null) return abstractFactory;
        else throw new Exception("this parser doesn't exist");
   }
}
