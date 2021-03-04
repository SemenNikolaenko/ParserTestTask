package Errors;

public enum Errors {
    READ_ERROR("произошла ошибка чтения парсинг не удался"),DONT_ENOUGH_INPUT_DATA("DONT_ENOUGH_INPUT_DATA");
    String message;

    Errors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
