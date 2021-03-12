package converter;

import Errors.Errors;
import org.springframework.stereotype.Component;
import output.OutputObject;

import java.util.*;

@Component
public class ConvertToOutputFormat implements Converter {

@Override
    public OutputObject getReadyObject(String lineReadyToConvert) {
        OutputObject readyObject;

            if (lineReadyToConvert.contains(Errors.READ_ERROR.getMessage())) {
                readyObject = new OutputObject.Builder().setResult(Errors.READ_ERROR.getMessage()).build();

            } else if (lineReadyToConvert.contains(Errors.DONT_ENOUGH_INPUT_DATA.getMessage())) {
                 readyObject = new OutputObject.Builder().setResult(Errors.DONT_ENOUGH_INPUT_DATA.getMessage()).build();

            } else {
                try {
                    Map<String, String> params = getParamsForFutureObject(lineReadyToConvert);

                readyObject = new OutputObject.Builder()
                        .setId(Integer.parseInt(params.get("orderId")))
                        .setAmount(Double.parseDouble(params.get("amount")))
                        .setFilename(params.get("filename"))
                        .setLine(Integer.parseInt(params.get("line")))
                        .setResult(params.get("result"))
                        .setComment(params.get("comment"))
                        .build();
                }catch (NumberFormatException e){
                    return readyObject = new OutputObject.Builder().setResult(Errors.INVALID_DATA_INPUT.getMessage()).build();
                }
            }
        return readyObject;
    }

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

    @Override
    public String getCommentFromParsingString(String convertedLine) {
        String comment = convertedLine
                .substring(convertedLine.indexOf("comment "), convertedLine.indexOf("filename"))
                .substring(8)
                .trim();
        return comment;
    }

    @Override
    public int getOrderIdtFromParsingString(String convertedLine) throws NumberFormatException{
        String orderIdString = convertedLine
                .substring(convertedLine.indexOf("orderId "), convertedLine.indexOf("amount"))
                .substring(8)
                .trim();
            int orderId = Integer.parseInt(orderIdString);
        return orderId;

    }

    @Override
    public double getAmountFromParsingString(String convertedLine) throws NumberFormatException {
        String amountStringFormat = convertedLine
                .substring(convertedLine.indexOf("amount "), convertedLine.indexOf("currency"))
                .substring(7)
                .trim();
            double amount = Double.parseDouble(amountStringFormat);
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
    public int getLineNumberFromParsingString(String convertedLine) {
        String lineStringFormat = convertedLine
                .substring(convertedLine.indexOf("line "))
                .substring(5)
                .trim();
        int line = Integer.parseInt(lineStringFormat);

        return line;
    }
}

