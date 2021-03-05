package parser.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import parser.CustomParser;

@Component
public class CsvParserFactory extends AbstractFactory {

    private CustomParser csvParser;

    public CsvParserFactory() {
    }

    @Autowired
    public void setCsvParser(@Qualifier("csvParser") CustomParser csvParser) {
        this.csvParser = csvParser;
    }

    @Override
    public CustomParser createParser() {
        return csvParser;
    }


}
