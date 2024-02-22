import java.util.Scanner;

public class media {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        float media = scan.nextFloat();
        int reddito = scan.nextInt();
        
        float tax = 18;
        if (media < 7) {
            tax += 25;
        } else if (media > 7 && media < 8) {
            tax += 17.5;
        }

        if (reddito < 16000) {
            tax -= tax * 40 / 100;
        }

        System.out.println(tax);
    }
}
