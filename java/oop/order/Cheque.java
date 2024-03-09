package java.oop.order;

public class Cheque extends Payment {
    private String name;
    private String bankID;

    public Cheque(String name, String bankID, float amount) {
        super(amount);
        this.name = name;
        this.bankID = bankID;
    }

    public boolean authorized() {
        return true;
    }

    public String getName() {
      return this.name;
    }
    public void setName(String value) {
      this.name = value;
    }

    public String getBankID() {
      return this.bankID;
    }
    public void setBankID(String value) {
      this.bankID = value;
    }
}
