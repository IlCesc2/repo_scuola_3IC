package verifica_1;
import java.util.Scanner;

public class verifica3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Lunghezza: ");
        int N = scan.nextInt();
        int[] vec = new int[N]; //{1,2,3,4,5,6,7,8,9,10};
        double media = 0.0;
        
        for (int i = 0; i < N; i++) {
            System.out.println("Inserisci elemento: ");

           vec[i] = scan.nextInt();
           media+= vec[i];
        }
        
        media /= N;
        double max = Double.NEGATIVE_INFINITY;
        int maxPos = 0;
        for (int i = 0; i < vec.length; i++) {
            if (max< vec[i]) {
                max = vec[i]; 
                maxPos = i;
            }
            if (vec[i]< media) vec[i] =0; // setto elemento a 0 se minore alla media
        }

        System.out.println("Vettore: ");
        for (int i = 0; i < vec.length; i++) {
            System.out.println(vec[i]);
        }

        System.out.println("Posizione: ");
        System.out.println(maxPos);
    }
}
