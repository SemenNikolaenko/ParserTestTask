package parser;

import java.io.IOException;
import java.util.List;

public interface CustomParser {

    public CustomParser setWorkParam(String filePath);
    public List<String> parsingLinesFromFile() throws IOException;
    public List<String> getWorkResult();
    public List<String> readAllLinesInFile() throws IOException;
    public String getFileName();
}