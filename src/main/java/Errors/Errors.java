package Errors;

public enum Errors {
    READ_ERROR("ERROR_READ_INPUT_DATA"),DONT_ENOUGH_INPUT_DATA("DONT_ENOUGH_INPUT_DATA"),
    INVALID_DATA_INPUT("TEXT_CONTAINS_INVALID_DATA");
    String message;

    Errors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
