package parser.factory;


import parser.CustomParser;

/**
 * фабрика возвращающая JSON парсер
 */
public class JsonParserFactory extends AbstractFactory {


    private final CustomParser jsonParser;

    public JsonParserFactory(CustomParser jsonParser) {
        this.jsonParser = jsonParser;
    }


    @Override
    public CustomParser createParser() {
        return jsonParser;
    }
}
