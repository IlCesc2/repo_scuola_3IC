package java.oop.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Order {
    private String date;
    private String status;
    List<OrderDetail> details = new ArrayList<OrderDetail>();
    private Payment payment;


    public Order(String date, String status,String option, float amount) {
        this.date = date;
        this.status = status;
    }
    public void handlePayment(String option, float amount) {
        Scanner scan = new Scanner(System.in);
        switch (option) {
            case "cash":
                this.payment= new Cash(amount);
                break;
            case "credit":
                String number = scan.nextLine();
                String type = scan.nextLine();
                String expDate = scan.nextLine();

                this.payment= new Credit(number,type, expDate, amount);
                break;
            case "cheque":
                String name = scan.nextLine();
                String bankID = scan.nextLine();
                this.payment= new Cheque(name, bankID, amount);
                break;

            default:
                break;
        }
    }

    public double calcSubTotal(){
        double subt =0;
        for (OrderDetail orderDetail : details) {
            subt+= orderDetail.calcSubTotal();
        }
        return subt;
    }
    public double calcTotal(){
        return calcSubTotal() * calcTax();
    }
    
    public double calcTax(){
        double tax =0;
        for (OrderDetail orderDetail : details) {
            tax+= orderDetail.calcTax();
        }
        return tax;
    }
    public double calcTotalWeight(){
        double weight =0;
        for (OrderDetail orderDetail : details) {
            weight+= orderDetail.calcWeight();
        }
        return weight;
    }
    public void addDetail() {
        Scanner scan = new Scanner(System.in);
        int quantity = scan.nextInt();
        String taxStatus = scan.nextLine();
        OrderDetail orderDetail = new OrderDetail(quantity, taxStatus);
        details.add(orderDetail);
    }
}
