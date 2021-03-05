package parser.implementation;

import Errors.Errors;
import org.springframework.stereotype.Component;
import parser.CustomParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class JsonParser implements CustomParser {
    protected String readError;
    protected File file;
    String filePath;
    List<String> separateLines;
    Charset charsetForJsoNReading = UTF_8;

    public JsonParser() {

    }

    public void setWorkParam(String filePath) {
        file = Paths.get(filePath).toFile();
        this.filePath = filePath;
        separateLines = new ArrayList<>();
//        return this;
    }

    @Override
    public List<String> parsingLinesFromFile() throws IOException {
        List<String> inputLines = readAllLinesInFile();
        List<List<String>> massParam = new ArrayList<>();
        for (int i = 0; i < inputLines.size(); i++) {
            massParam.add(
                    Arrays.stream(inputLines.get(i)
                            .split("[{}\":,]")).filter(v -> !v.equals(""))
                            .collect(Collectors.toList())
            );
        }
        int temp = 0;
        while (temp < massParam.size()) {
            if (
                    massParam.get(temp).contains("orderId") &&
                            massParam.get(temp).contains("currency") &&
                            massParam.get(temp).contains("amount") &&
                            massParam.get(temp).contains("comment")
            ) {
                separateLines.add(massParam.get(temp).stream().collect(Collectors.joining(" ")));
                temp++;
            } else {
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
                String temp = parsingFileWithAdditionalInfo.get(i) + " filename " + getFileName() + " line " + (i + 1) + " ";
                parsingFileWithAdditionalInfo.set(i, temp);
            }
        } catch (IOException e) {
            readError = "произошла ошибка чтения парсинг не удался";
            parsingFileWithAdditionalInfo.add("result " + readError);
        }
        return parsingFileWithAdditionalInfo;
    }

    @Override
    public List<String> readAllLinesInFile() throws IOException {
        return Files.readAllLines(Paths.get(filePath), charsetForJsoNReading);
    }

    @Override
    public String getFileName() {
        return file.getName();
    }


//    @Override
//    public void run() {
//
//    }

}
