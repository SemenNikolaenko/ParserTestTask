package output;

public class OutputObject {
    private int id = 0;
    private double amount = 0;
    private String comment = "";
    private String filename = "";
    private int line = 0;
    public String result;

    /*
    формат готового для вывода в консоль объекта
     */
    public static class Builder {
        private OutputObject object;

        public Builder() {
            this.object = new OutputObject();
        }

        public Builder setId(int id) {
            this.object.id = id;
            return this;
        }

        public Builder setAmount(double amount) {
            this.object.amount = amount;
            return this;
        }

        public Builder setComment(String comment) {
            this.object.comment = comment;
            return this;
        }

        public Builder setFilename(String filename) {
            this.object.filename = filename;
            return this;
        }

        public Builder setLine(int line) {
            this.object.line = line;
            return this;
        }

        public Builder setResult(String result) {
            this.object.result = result;
            return this;
        }

        public OutputObject build() {
            return this.object;
        }


    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }

    public String getFilename() {
        return filename;
    }

    public int getLine() {
        return line;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":%d,\"amount\":%.3f,\"comment\":%s,\"filename\":%s,\"line\":%d,\"result\":%s}", id, amount, comment, filename, line, result);
    }
}
