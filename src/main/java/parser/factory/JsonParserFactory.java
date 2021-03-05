package parser.factory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import parser.CustomParser;

@Component
public class JsonParserFactory extends AbstractFactory {


    private CustomParser jsonParser;

    public JsonParserFactory() {
    }

    @Autowired
    public JsonParserFactory(@Qualifier("jsonParser") CustomParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    @Override
    public CustomParser createParser() {
        return jsonParser;
    }
}
