package parser;


import java.util.List;

/**
 * ������ ��������� ��������� �������� ������ �������
 */
public interface CustomParser {
    /**
     * � ���� ������ ���������� ������������� ������� ���������� �������
     *
     * @param filePath
     */
    public void setWorkParam(String filePath);

    /**
     * ������ ����� ������ ������� ������ ������������� ������� ����� ��������, ��� ���������� ������
     *
     * @return List �� �������� ������������� �������
     */
    public List<String> getParsedString();


}