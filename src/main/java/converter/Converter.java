package converter;

import output.OutputObject;
import parser.CustomParser;

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
     * все параметры дл¤ создани¤ объекта получаютс¤ из метода
     * {@link Converter#getParamsForFutureObject(String)}
     */
    public OutputObject getReadyObject(String linesReadyToConvert);

    /**
     *
     * @param convertedLine это преобразована¤ строка которую можно получить в классе {@link CustomParser#getParsedString()}
     * @return Map<String,String> на выходе получаетс¤ мапа ключи который ¤вл¤ютс¤ именами параметров
     * а значени¤ соответственно значени¤ми
     */
    public Map<String, String> getParamsForFutureObject(String convertedLine);

}
