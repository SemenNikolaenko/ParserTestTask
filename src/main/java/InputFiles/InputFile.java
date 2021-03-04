package InputFiles;

import org.springframework.stereotype.Component;

@Component
public  class InputFile {
    int orderId;
    double amount;
    String currency;
    String comment;



    public InputFile(int id, double amount, String currency, String comment) {
        this.orderId = id;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
    }

    public InputFile() {
    }

    public int getId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getComment() {
        return comment;
    }
}
