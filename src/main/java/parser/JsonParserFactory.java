package parser;

import org.springframework.context.ApplicationContext;

public class JsonParserFactory extends AbstractFactory {
   final String filePath;
   final ApplicationContext context;

    public JsonParserFactory(String filePath, ApplicationContext context) {
        this.filePath = filePath;
        this.context = context;
    }

    @Override
    public CustomParser createParser() throws Exception {
        CustomParser customParser = null;
        if (filePath.endsWith(".json")){
            customParser= context.getBean(JsonParser.class);
        }
        if (customParser==null) throw new Exception("this parser doesn't exist");
        else return customParser.setWorkParam(filePath);
    }
}
