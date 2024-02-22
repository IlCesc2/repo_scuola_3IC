package recursion;
import java.util.Scanner;

public class pot_ric {
    public static void main(String[] args) {
          Scanner scan = new Scanner(System.in);

          System.out.println("Base:");
          int base = scan.nextInt();
          System.out.println("Base:");
          int esp = scan.nextInt();

          System.out.println("Risultato: "+pow(base, esp));
    }
    public static int pow(int base, int esp) {
        if (esp ==0) return 1;
        return base * pow(base, esp-1);
    }
}