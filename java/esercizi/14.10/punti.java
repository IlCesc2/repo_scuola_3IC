import java.util.Scanner;

public class punti {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int spesa = scan.nextInt();

        if (spesa <=50) {
            System.out.println(spesa/5);
        } else {
            System.out.println( (spesa-50) /4 + 10);// 50/10
        }
    }
}
