import java.util.Scanner;

public class prime {
    public static void main(String[] args) {
        System.out.println(isPrime());
    }
    public static String isPrime() {
        try (Scanner scan = new Scanner(System.in)) {
            int prime;
            do {
                prime = scan.nextInt();
            } while (prime <0);
            
            for(int i = 2;  i < prime/2; i++) {
                if(prime %i ==0) {
                    return "Non è primo";
                }
            }
        }
        return "E' primo";
    }
}
