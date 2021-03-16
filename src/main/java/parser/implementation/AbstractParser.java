package parser.implementation;

import Errors.Errors;
import parser.CustomParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * основной класс парсера, в котором определены методы работы с файлами из вне
 */
public abstract class AbstractParser implements CustomParser {
    //файл полученный из консоли
    protected File file;
    //путь файла полученный из консоли
    String filePath;
    //не все файлы могут одинаково хорошо читаться, для некоторых необходимо выставить кодировку
    Charset charsetForReading = Charset.defaultCharset();
    List<String> separateLines;

    /**
     * каждый файл имеет свой формат данных, данный метод позволяет распарсить строку на отдельные части
     *
     * @return List с отдельными частями строки для дальнейшей работы
     * @throws IOException
     */
    public abstract List<String> parsingLinesFromFile() throws IOException;

    /**
     * данный метод вызывается после работы метода {@link AbstractParser#parsingLinesFromFile()}
     * для каждой распарсеной строки добавляет доп информацию об имени файла и номере строки
     *
     * @return
     */
    @Override
    public List<String> getParsedString() {
        List<String> parsingFileWithAdditionalInfo = new ArrayList<>();
        try {
            parsingFileWithAdditionalInfo = parsingLinesFromFile();
            for (int i = 0; i < parsingFileWithAdditionalInfo.size(); i++) {
                //добавление доп информации к строке
                String temp = parsingFileWithAdditionalInfo.get(i) + " filename " + getFileName() + " line " + (i + 1) + " ";
                parsingFileWithAdditionalInfo.set(i, temp);
            }
        } catch (IOException e) {
            // в случае появление исключения в файл добавляет сообщение об ошибке для дальнейшей обработки
            parsingFileWithAdditionalInfo.add("result " + Errors.READ_ERROR.getMessage());
        }
        return parsingFileWithAdditionalInfo;
    }

    /**
     * читает из файла все строки
     *
     * @return List с прочитанными строками из файла
     * @throws IOException
     */
    public List<String> readAllLinesInFile() throws IOException {
        return Files.readAllLines(Paths.get(filePath), charsetForReading);
    }

    /**
     * позволяет получить имя файла
     *
     * @return String имя файла
     */
    public String getFileName() {
        return file.getName();
    }

}
