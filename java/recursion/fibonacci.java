package recursion;

import java.util.Scanner;

public class fibonacci {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Numero:");
        int base = scan.nextInt();

        System.out.println("Risultato: "+fibonacci(base));
    }
    public static int fibonacci(int n) {
        if (n<2) return n;
        return fibonacci(n-1) + fibonacci(n-2);
    }
}
