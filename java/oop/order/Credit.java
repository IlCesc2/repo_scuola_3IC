package java.oop.order;


public class Credit extends Payment {
    private String number;
    private String type;
    private String expDate;

    public Credit(String number, String type, String expDate, float amount) {
        super(amount);
        this.number = number;
        this.type = type;
        this.expDate = expDate;
    }
    public boolean authorized() {
        return true;
    }

    public String getNumber() {
      return this.number;
    }
    public void setNumber(String value) {
      this.number = value;
    }

    public String getType() {
      return this.type;
    }
    public void setType(String value) {
      this.type = value;
    }

    public String getExpDate() {
      return this.expDate;
    }
    public void setExpDate(String value) {
      this.expDate = value;
    }
}
