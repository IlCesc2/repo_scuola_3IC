package java.oop.order;

public class Item {
    private double shippingWeight;
    private String description;

    public Item(double shippingWeight, String description) {
        this.shippingWeight = shippingWeight;
        this.description = description;
    }

    public double getShippingWeight() {
      return this.shippingWeight;
    }
    public void setShippingWeight(double value) {
      this.shippingWeight = value;
    }

    public String getDescription() {
      return this.description;
    }
    public void setDescription(String value) {
      this.description = value;
    }

    public double getPriceForQuantity() {
        return 0.0;
    }
    public double getTax() {
        return 0.0;
    }
    public boolean inStock() {
        return true;
    }
}
