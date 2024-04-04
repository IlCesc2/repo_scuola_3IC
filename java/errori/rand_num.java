import java.util.Random;
import java.util.Scanner;

public class rand_num {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserire Valore:");
        try {
            int num = scan.nextInt();
            Random rand = new Random();
            int r = rand.nextInt(4);
            System.out.println("Risultato:"+ num/r);

        } catch (Exception e) {
            System.out.println("Errore! Numero generato randomicamente è 0");
        }
    }
}
