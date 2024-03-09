package java.oop.order;

public class Customer {
    private String name;
    private String address;

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }
    

    public String getName() {
      return this.name;
    }
    public void setName(String value) {
      this.name = value;
    }

    public String getAddress() {
      return this.address;
    }
    public void setAddress(String value) {
      this.address = value;
    }
}
