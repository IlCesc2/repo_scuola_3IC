import java.util.Arrays;
import java.util.Random;

public class Esercizio4 {
    public static void main(String[] args) {
        /*
        4) Data una matrice 6×6 verificare se è simmetrica rispetto alla diagonale principale. (per diagonale principale si intende quella che va dall’elemento [0][0] all’elemento [5][5]).
        Una matrice si dice simmetrica rispetto rispetto alla diagonale principale se è uguale alla sua trasposta.
        La trasposta di una matrice si ottiene prendendo in ordine le righe della matrice originale e facendole diventare le colonne della matrice trasposta.
        */

        int N = 6;
        int[][] arr = new int[N][N];
        Random rand = new Random();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = (int) rand.nextInt(100);
            }
        }
        for (int[] el: arr) System.out.println(Arrays.toString(el));


        boolean isSimmetric = true;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] != arr[j][i]) {
                    isSimmetric = false;
                    break;
                }
            }
        }
        System.out.println(isSimmetric);
    }
}
