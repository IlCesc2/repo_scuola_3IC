import java.util.InputMismatchException;
import java.util.Scanner;
public class vector_set {


    public static void main(String[] args) {
        int N = 5;
        int[] vec = new int[N]; 
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Inserire Valore:");
            int num = scan.nextInt();
            System.out.println("Inserire Posizione:");
            int pos = scan.nextInt();
            vec[pos] = num;
        } catch (InputMismatchException e ) {
            System.out.println("Valore inserito invalido, inserire un numero intero");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Errore! Posizione invalida");
        } 
    }


}
