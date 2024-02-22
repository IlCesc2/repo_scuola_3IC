package verifica_1;
import java.util.Scanner;

public class verifica1 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Prezzo Base: ");
        int base = scan.nextInt();

        System.out.println("Kilometri: ");
        int km = scan.nextInt();

        System.out.println("N Accessori: ");
        int acc = scan.nextInt();

        double tot = base+ (km*0.075) + (acc*155);

        tot -= tot*15 / 100; // sconto

        System.out.println("Totale: ");
        System.out.println(tot);
    }
}
