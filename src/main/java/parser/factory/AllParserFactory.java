package parser.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AllParserFactory {

    AbstractFactory csvFactory;
    AbstractFactory jsonFactory;

    public AllParserFactory() {
    }

    @Autowired
    public void setCsvFactory(@Qualifier("csvParserFactory")AbstractFactory csvFactory) {
        this.csvFactory = csvFactory;
    }

    @Autowired
    public AllParserFactory(@Qualifier("jsonParserFactory") AbstractFactory jsonFactory) {
        this.jsonFactory = jsonFactory;
    }

    public  AbstractFactory getFactory(String filePath) throws Exception {
        if (filePath.endsWith(".json")) return jsonFactory;
        else if (filePath.endsWith("csv"))return csvFactory;
        else throw new Exception("No parser implementation");
//        if (abstractFactory != null) return abstractFactory;
//        else throw new Exception("this parser doesn't exist");
    }
}
