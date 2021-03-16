package parser.implementation;

import Errors.Errors;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvParser extends AbstractParser {


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
        List<String[]> massParam = new ArrayList<>();
        // в данном цикле происходит разбор строки типа csv
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
}
