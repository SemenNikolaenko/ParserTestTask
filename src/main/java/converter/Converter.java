package converter;

import output.OutputObject;
import parser.CustomParser;

import java.util.List;
import java.util.Map;

/**
 * @author Semen Nikolaenko
 * @version 1
 */
interface Converter {
    /**
     *
     * @param linesReadyToConvert
     * @return OutputObject readyObject
     * метод возвращает готовый объект требуемого формата
     * все параметры для создания объекта получаются из метода
     * {@link Converter#getParamsForFutureObject(String)}
     */
    public OutputObject getReadyObject(String linesReadyToConvert);

    /**
     *
     * @param convertedLine это преобразованая строка которую можно получить в классе {@link CustomParser#getWorkResult()}
     * @return Map<String,String> на выходе получается мапа ключи который являются именами параметров
     * а значения соответственно значениями
     */
    public Map<String, String> getParamsForFutureObject(String convertedLine);

}
