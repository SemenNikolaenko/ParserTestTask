package parser;


import java.util.List;

/**
 * данный интерфейс описывает основную работу парсера
 */
public interface CustomParser {
    /**
     * в этом методе происходит инициализация рабочих параметров парсера
     *
     * @param filePath
     */
    public void setWorkParam(String filePath);

    /**
     * данный метод выдает готовую строку определенного формата после парсинга, для дальнейшей работы
     *
     * @return List со строками определенного формата
     */
    public List<String> getParsedString();


}