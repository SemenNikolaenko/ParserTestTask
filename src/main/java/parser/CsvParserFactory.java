package parser;

import org.springframework.context.ApplicationContext;

public class CsvParserFactory extends AbstractFactory {
    String filePath;
   final ApplicationContext context;

    public CsvParserFactory(String filePath, ApplicationContext context) {
        this.filePath = filePath;
        this.context = context;
    }

    @Override
    public  CustomParser createParser() throws Exception {
        CustomParser customParser = null;
        if (filePath.endsWith(".csv"))
            customParser = context.getBean(CsvParser.class);
        if (customParser==null) throw new Exception("this parser doesn't exist");
        else return customParser.setWorkParam(filePath);
    }


}
