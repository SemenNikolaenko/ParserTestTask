package parser.implementation;

import Errors.Errors;
import org.springframework.stereotype.Component;
import parser.CustomParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvParser implements CustomParser {
    protected String readError;
    protected File file;
    String filePath;
    List<String> separateLines;
    Charset charsetForCsvReading = Charset.defaultCharset();


    public CsvParser() {
    }

    @Override
    public void setWorkParam(String filePath) {
        file = Paths.get(filePath).toFile();
        this.filePath = filePath;
        separateLines = new ArrayList<>();
//        return this;
    }

    @Override
    public List<String> parsingLinesFromFile() throws IOException {
        List<String> inputLines = readAllLinesInFile();
        List<String[]> massParam = new ArrayList<>();
        for (int i = 0; i < inputLines.size(); i++) {
            massParam.add(inputLines.get(i).split(","));
        }
        int temp = 0;
        while (temp < massParam.size()) {
            String[] strings = massParam.get(temp);
            if (strings.length == 4) {
                separateLines.add("orderId " + strings[0] + " amount " + strings[1] + " currency " + strings[2] + " comment " + strings[3]);
                temp++;
            } else if (strings.length < 4) {
                separateLines.add(Errors.DONT_ENOUGH_INPUT_DATA.getMessage());
                temp++;
            }
        }
        return separateLines;
    }

    @Override
    public List<String> getWorkResult() {
        List<String> parsingFileWithAdditionalInfo = new ArrayList<>();
        try {
            parsingFileWithAdditionalInfo = parsingLinesFromFile();
            for (int i = 0; i < parsingFileWithAdditionalInfo.size(); i++) {
                String temp = parsingFileWithAdditionalInfo.get(i) + " filename " + getFileName() + " line " + (i + 1)+" ";
                parsingFileWithAdditionalInfo.set(i, temp);
            }
        } catch (IOException e) {
            parsingFileWithAdditionalInfo.add("result " + Errors.READ_ERROR.getMessage());
        }
        return parsingFileWithAdditionalInfo;
    }

    @Override
    public List<String> readAllLinesInFile() throws IOException {
        return Files.readAllLines(Paths.get(filePath), charsetForCsvReading);
    }

    @Override
    public String getFileName() {
        return file.getName();
    }


}
