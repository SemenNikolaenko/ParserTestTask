package Errors;

public enum Errors {
    READ_ERROR("��������� ������ ������ ������� �� ������"),DONT_ENOUGH_INPUT_DATA("DONT_ENOUGH_INPUT_DATA"),
    INVALID_DATA_INPUT("������� �������� ������ � ��������� ����");
    String message;

    Errors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
