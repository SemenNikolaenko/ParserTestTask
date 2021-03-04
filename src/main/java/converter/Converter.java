package converter;

import output.OutputObject;

import java.util.List;
import java.util.Map;

 interface Converter {
    public List<OutputObject> getReadyObject(List<String> linesReadyToConvert);
    public Map<String, String> getParamsForFutureObject(String convertedLine);
     public String getCommentFromParsingString(String convertedLine);
     public String getOrderIdtFromParsingString(String convertedLine);
     public String getAmountFromParsingString(String convertedLine);
     public String getFileNameFromParsingString(String convertedLine);
     public String getLineNumberFromParsingString(String convertedLine);
}
