package parser.factory;

public class AllParserFactory {

    static AbstractFactory csvFactory;
    static AbstractFactory jsonFactory;


    public AllParserFactory(AbstractFactory csvFactory, AbstractFactory jsonFactory) {
        AllParserFactory.csvFactory = csvFactory;
        AllParserFactory.jsonFactory = jsonFactory;
    }

    /**
     * на основании расширения файла выбирается фабрика определенного парсера
     *
     * @param filePath путь до требуемого файла
     * @return возвращает фабрику конкретнонго парсера
     * @throws Exception
     */
    public static AbstractFactory getFactory(String filePath) throws Exception {
        if (filePath.endsWith(".json")) return jsonFactory;
        else if (filePath.endsWith("csv")) return csvFactory;
        else throw new Exception("No parser implementation");

    }
}
