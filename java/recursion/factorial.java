package recursion;
import java.util.Scanner;

public class factorial {
    public static void main(String[] args) {
          Scanner scan = new Scanner(System.in);
          System.out.println("Numero:");
          int base = scan.nextInt();
        // 5 : 1*2*3*4*5
          System.out.println("Risultato: "+factorial(base));
    }
    public static int factorial(int base) {
        if (base ==1) return 1;
        return base * factorial(base-1);
    }
}