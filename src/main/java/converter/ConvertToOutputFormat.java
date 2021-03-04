package converter;

import Errors.Errors;
import org.springframework.stereotype.Component;
import output.OutputObject;

import java.util.*;

@Component
public class ConvertToOutputFormat implements Converter {

    @Override
    public List<OutputObject> getReadyObject(List<String> linesReadyToConvert) {
        List<String> linesToConvert = linesReadyToConvert;
        List<OutputObject> readyObjects = new ArrayList<>();
        for (int i = 0; i < linesToConvert.size(); i++) {
            if (linesToConvert.get(i).contains(Errors.READ_ERROR.getMessage())) {
                OutputObject object = new OutputObject.Builder().setResult(Errors.READ_ERROR.getMessage()).build();
                readyObjects.add(object);
            } else if (linesToConvert.get(i).contains(Errors.DONT_ENOUGH_INPUT_DATA.getMessage())) {
                OutputObject object = new OutputObject.Builder().setResult(Errors.DONT_ENOUGH_INPUT_DATA.getMessage()).build();
                readyObjects.add(object);
            } else {
                Map<String, String> params = getParamsForFutureObject(linesToConvert.get(i));
                OutputObject object = new OutputObject.Builder()
                        .setId(Integer.parseInt(params.get("orderId")))
                        .setAmount(Double.parseDouble(params.get("amount")))
                        .setFilename(params.get("filename"))
                        .setLine(Integer.parseInt(params.get("line")))
                        .setResult(params.get("result"))
                        .setComment(params.get("comment"))
                        .build();
                readyObjects.add(object);
            }
        }
        return readyObjects;
    }

    @Override
    public Map<String, String> getParamsForFutureObject(String convertedLine) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("orderId", getOrderIdtFromParsingString(convertedLine));
        paramsMap.put("amount", getAmountFromParsingString(convertedLine));
        paramsMap.put("comment", getCommentFromParsingString(convertedLine));
        paramsMap.put("filename", getFileNameFromParsingString(convertedLine));
        paramsMap.put("line", getLineNumberFromParsingString(convertedLine));
        paramsMap.put("result", "OK");

        return paramsMap;
    }

    @Override
    public String getCommentFromParsingString(String convertedLine) {
        String comment = convertedLine
                .substring(convertedLine.indexOf("comment "), convertedLine.indexOf("filename"))
                .substring(8)
                .trim();
        return comment;
    }

    @Override
    public String getOrderIdtFromParsingString(String convertedLine) {
        String orderId = convertedLine
                .substring(convertedLine.indexOf("orderId "), convertedLine.indexOf("amount"))
                .substring(8)
                .trim();
        return orderId;

    }

    @Override
    public String getAmountFromParsingString(String convertedLine) {
        String amount = convertedLine
                .substring(convertedLine.indexOf("amount "), convertedLine.indexOf("currency"))
                .substring(7)
                .trim();
        return amount;
    }

    @Override
    public String getFileNameFromParsingString(String convertedLine) {
        String fileName = convertedLine
                .substring(convertedLine.indexOf("filename "), convertedLine.indexOf("line"))
                .substring(9)
                .trim();
        return fileName;
    }

    @Override
    public String getLineNumberFromParsingString(String convertedLine) {
        String line = convertedLine
                .substring(convertedLine.indexOf("line "))
                .substring(5)
                .trim();
        return line;
    }
}

