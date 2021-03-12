package parser.factory;

import parser.CustomParser;

public class CsvParserFactory extends AbstractFactory {

    private final CustomParser csvParser;

    public CsvParserFactory(CustomParser csvParser) {
        this.csvParser = csvParser;
    }


    @Override
    public CustomParser createParser() {
        return csvParser;
    }


}
