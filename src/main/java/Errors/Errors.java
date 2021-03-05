package Errors;

public enum Errors {
    READ_ERROR("Произошла ошибка чтения парсинг не удался"),DONT_ENOUGH_INPUT_DATA("DONT_ENOUGH_INPUT_DATA"),
    INVALID_DATA_INPUT("Введены неверные данные в некоторые поля");
    String message;

    Errors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
