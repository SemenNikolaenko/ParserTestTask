package converter;

import Errors.Errors;
import org.springframework.stereotype.Component;
import output.OutputObject;

import java.util.*;

/**
 * @version 1
 */
@Component
public class ConvertToOutputFormat implements Converter {


    @Override
    public OutputObject getReadyObject(String lineReadyToConvert) {
        OutputObject readyObject;

        /*
        если строка после парсинга содержит сообщение об ошибке, предарительно помещенное
        в нее парсером, то готовый объект заполняется этим сообщением, для дальнешего вывода статуса в консоль
         */
        if (lineReadyToConvert.contains(Errors.READ_ERROR.getMessage())) {
            readyObject = new OutputObject.Builder().setResult(Errors.READ_ERROR.getMessage()).build();
                /*
                если в строке недостаточно входных данных тело объекта заполняется сообщением об ошибке
                 */
        } else if (lineReadyToConvert.contains(Errors.DONT_ENOUGH_INPUT_DATA.getMessage())) {
            readyObject = new OutputObject.Builder().setResult(Errors.DONT_ENOUGH_INPUT_DATA.getMessage()).build();
            //если ошибок в строке не найдено, происходит конвертация в заданный формат
        } else {
            try {
                //из переопределенного метода getParamsForFutureObject вытаскиваются параметры
                Map<String, String> params = getParamsForFutureObject(lineReadyToConvert);

                //происходит создание требуемого объекта с помощью параметров полученных из мапы

                readyObject = new OutputObject.Builder()
                        .setId(Integer.parseInt(params.get("orderId")))
                        .setAmount(Double.parseDouble(params.get("amount")))
                        .setFilename(params.get("filename"))
                        .setLine(Integer.parseInt(params.get("line")))
                        .setResult(params.get("result"))
                        .setComment(params.get("comment"))
                        .build();
            } catch (NumberFormatException e) {
              /*
                если в первоначальной строке были введены неверные параметры
                например вместо числа была введена строка, парсер такую ошибку не заметил,
                но на этапе конвертации такая ошибка фиксируется и обрабатывается
               */
                return readyObject = new OutputObject.Builder().setResult(Errors.INVALID_DATA_INPUT.getMessage()).build();
            }
        }
        return readyObject;
    }

    /*
    с помощью данного метода вызываются более простые методы для преобразования каждого параметра в готовый выходной формат
    и на выходе получается Map с набором параметров
     */
    @Override
    public Map<String, String> getParamsForFutureObject(String convertedLine) throws NumberFormatException {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("orderId", String.valueOf(getOrderIdtFromParsingString(convertedLine)));
        paramsMap.put("amount", String.valueOf(getAmountFromParsingString(convertedLine)));
        paramsMap.put("comment", getCommentFromParsingString(convertedLine));
        paramsMap.put("filename", getFileNameFromParsingString(convertedLine));
        paramsMap.put("line", String.valueOf(getLineNumberFromParsingString(convertedLine)));
        paramsMap.put("result", "OK");
        return paramsMap;
    }

    /**
     * из строки после парсинга извлекается первый параметр, для этого используется {@link String#substring(int, int)}
     * т.к. мы знаем что в строке есть ключевые слова-имена параметров
     *
     * @param convertedLine строка после парсинга
     * @return String готовый первый параметр
     */
    private String getCommentFromParsingString(String convertedLine) {
        String comment = convertedLine
                .substring(convertedLine.indexOf("comment "), convertedLine.indexOf("filename"))
                .substring(8)
                .trim();
        return comment;
    }

    /**
     * из строки после парсинга извлекается первый параметр, для этого используется {@link String#substring(int, int)}
     * т.к. мы знаем что в строке есть ключевые слова-имена параметров
     *
     * @param convertedLine строка после парсинга
     * @return int готовый второй параметр
     * @throws NumberFormatException выбрасывается при неверно введенном параметре
     */
    private int getOrderIdtFromParsingString(String convertedLine) throws NumberFormatException {
        String orderIdString = convertedLine
                .substring(convertedLine.indexOf("orderId "), convertedLine.indexOf("amount"))
                .substring(8)
                .trim();
        int orderId = Integer.parseInt(orderIdString);
        return orderId;

    }

    /**
     * из строки после парсинга извлекается первый параметр, для этого используется {@link String#substring(int, int)}
     * т.к. мы знаем что в строке есть ключевые слова-имена параметров
     *
     * @param convertedLine строка после парсинга
     * @return double готовый третий параметр
     * @throws NumberFormatException выбрасывается при неверно введенном параметре
     *                               из строки после парсинга извлекается первый параметр, для этого используется {@link String#substring(int, int)}
     *                               т.к. мы знаем что в строке есть ключевые слова-имена параметров
     */
    private double getAmountFromParsingString(String convertedLine) throws NumberFormatException {
        String amountStringFormat = convertedLine
                .substring(convertedLine.indexOf("amount "), convertedLine.indexOf("currency"))
                .substring(7)
                .trim();
        double amount = Double.parseDouble(amountStringFormat);
        return amount;
    }

    /**
     * из строки после парсинга извлекается первый параметр, для этого используется {@link String#substring(int, int)}
     * т.к. мы знаем что в строке есть ключевые слова-имена параметров
     *
     * @param convertedLine строка после парсинга
     * @return String готовый четвертый параметр
     */
    private String getFileNameFromParsingString(String convertedLine) {
        String fileName = convertedLine
                .substring(convertedLine.indexOf("filename "), convertedLine.indexOf("line"))
                .substring(9)
                .trim();
        return fileName;
    }

    /**
     * из строки после парсинга извлекается первый параметр, для этого используется {@link String#substring(int)}
     * * т.к. мы знаем что в строке есть ключевые слова-имена параметров
     *
     * @param convertedLine строка после парсинга
     * @return int последний параметр
     */
    private int getLineNumberFromParsingString(String convertedLine) {
        String lineStringFormat = convertedLine
                .substring(convertedLine.indexOf("line "))
                .substring(5)
                .trim();
        int line = Integer.parseInt(lineStringFormat);

        return line;
    }
}

