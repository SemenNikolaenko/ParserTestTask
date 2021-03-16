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
 * �������� ����� �������, � ������� ���������� ������ ������ � ������� �� ���
 */
public abstract class AbstractParser implements CustomParser {
    //���� ���������� �� �������
    protected File file;
    //���� ����� ���������� �� �������
    String filePath;
    //�� ��� ����� ����� ��������� ������ ��������, ��� ��������� ���������� ��������� ���������
    Charset charsetForReading = Charset.defaultCharset();
    List<String> separateLines;

    /**
     * ������ ���� ����� ���� ������ ������, ������ ����� ��������� ���������� ������ �� ��������� �����
     *
     * @return List � ���������� ������� ������ ��� ���������� ������
     * @throws IOException
     */
    public abstract List<String> parsingLinesFromFile() throws IOException;

    /**
     * ������ ����� ���������� ����� ������ ������ {@link AbstractParser#parsingLinesFromFile()}
     * ��� ������ ����������� ������ ��������� ��� ���������� �� ����� ����� � ������ ������
     *
     * @return
     */
    @Override
    public List<String> getParsedString() {
        List<String> parsingFileWithAdditionalInfo = new ArrayList<>();
        try {
            parsingFileWithAdditionalInfo = parsingLinesFromFile();
            for (int i = 0; i < parsingFileWithAdditionalInfo.size(); i++) {
                //���������� ��� ���������� � ������
                String temp = parsingFileWithAdditionalInfo.get(i) + " filename " + getFileName() + " line " + (i + 1) + " ";
                parsingFileWithAdditionalInfo.set(i, temp);
            }
        } catch (IOException e) {
            // � ������ ��������� ���������� � ���� ��������� ��������� �� ������ ��� ���������� ���������
            parsingFileWithAdditionalInfo.add("result " + Errors.READ_ERROR.getMessage());
        }
        return parsingFileWithAdditionalInfo;
    }

    /**
     * ������ �� ����� ��� ������
     *
     * @return List � ������������ �������� �� �����
     * @throws IOException
     */
    public List<String> readAllLinesInFile() throws IOException {
        return Files.readAllLines(Paths.get(filePath), charsetForReading);
    }

    /**
     * ��������� �������� ��� �����
     *
     * @return String ��� �����
     */
    public String getFileName() {
        return file.getName();
    }

}
