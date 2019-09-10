package util;

public class FieldErrorMessage {
    private String field;
    private String messsage;

    public FieldErrorMessage(String field, String messsage) {
        this.field = field;
        this.messsage = messsage;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }
}
