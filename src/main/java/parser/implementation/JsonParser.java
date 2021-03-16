package parser.implementation;

import Errors.Errors;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * реализация парсера для чтения файлов формата JSON
 */
@Component
public class JsonParser extends AbstractParser {


    /**
     * в этом методе происходит инициализация рабочих параметров парсера
     *
     * @param filePath
     */
    @Override
    public void setWorkParam(String filePath) {
        file = Paths.get(filePath).toFile();
        this.filePath = filePath;
        separateLines = new ArrayList<>();
        charsetForReading = UTF_8;
    }

    /**
     * каждый файл имеет свой формат данных, данный метод позволяет распарсить строку на отдельные части
     *
     * @return List с отдельными частями строки для дальнейшей работы
     * @throws IOException
     */
    @Override
    public List<String> parsingLinesFromFile() throws IOException {
        List<String> inputLines = readAllLinesInFile();
        List<List<String>> massParam = new ArrayList<>();
        //в данном цикле происходит разбор строки типа JSON стандартными методами java
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
}
