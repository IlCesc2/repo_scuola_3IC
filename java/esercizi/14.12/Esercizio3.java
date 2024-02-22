import java.util.Arrays;
import java.util.Random;

public class Esercizio3 {
    public static void main(String[] args) {
       // 3) Memorizzare in un array bidimensionale 10 x 10 dei numeri casuali compresi tra zero e 100, stamparne il contenuto e individuare il valore maggiore ed il minore presenti  nella matrice.
        int N = 10;
        int[][] vect = new int[N][N];
        Random rand = new Random();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                vect[i][j] = (int) rand.nextInt(100);
                if (vect[i][j] < min) min = vect[i][j];
                if (vect[i][j]> max) max = vect[i][j];
            }
        }
        for (int[] el: vect) {
            System.out.println(Arrays.toString(el));
        }
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);

    }
}
