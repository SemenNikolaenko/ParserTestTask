package parser.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
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
