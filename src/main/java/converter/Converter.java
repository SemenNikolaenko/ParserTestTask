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
     * ����� ���������� ������� ������ ���������� �������
     * ��� ��������� ��� �������� ������� ���������� �� ������
     * {@link Converter#getParamsForFutureObject(String)}
     */
    public OutputObject getReadyObject(String linesReadyToConvert);

    /**
     *
     * @param convertedLine ��� �������������� ������ ������� ����� �������� � ������ {@link CustomParser#getWorkResult()}
     * @return Map<String,String> �� ������ ���������� ���� ����� ������� �������� ������� ����������
     * � �������� �������������� ����������
     */
    public Map<String, String> getParamsForFutureObject(String convertedLine);

}
