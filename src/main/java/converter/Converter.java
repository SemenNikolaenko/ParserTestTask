package converter;

import output.OutputObject;

import java.util.List;
import java.util.Map;

 interface Converter {
    public OutputObject getReadyObject(String linesReadyToConvert);
    public Map<String, String> getParamsForFutureObject(String convertedLine);
     public String getCommentFromParsingString(String convertedLine);
     public int getOrderIdtFromParsingString(String convertedLine);
     public double getAmountFromParsingString(String convertedLine);
     public String getFileNameFromParsingString(String convertedLine);
     public int getLineNumberFromParsingString(String convertedLine);
}
