package java.oop.order;

import java.util.Scanner;

public class OrderDetail {
    private int quantity;
    private String taxStatus;
    private Item item;

    public OrderDetail(int quantity, String taxStatus) {
        handleItem();
        this.quantity = quantity;
        this.taxStatus = taxStatus;
    }

    public void handleItem() {
        Scanner scan = new Scanner(System.in);
        double shippingWeight = scan.nextDouble();
        String description= scan.nextLine();
        this.item = new Item(shippingWeight, description);
    }
    public double calcSubTotal(){
        return 0.0;
    }
    public double calcWeight(){
        return 0.0;
    }
    public double calcTax(){
        return 0.0;
    }


    public int getQuantity() {
      return this.quantity;
    }
    public void setQuantity(int value) {
      this.quantity = value;
    }

    public String getTaxStatus() {
      return this.taxStatus;
    }
    public void setTaxStatus(String value) {
      this.taxStatus = value;
    }
}
