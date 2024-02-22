import java.util.Arrays;

public class Esercizi2 {
    //    2) Memorizzare in un array bidimensionale 10 x 10 la tavola pitagorica, quella delle tabelline, e stampare il contenuto della matrice (nella prima riga si dovrà trovare la tabellina del 1: 1 2 3 4 5 6 7 8 9 10).
    public static void main(String[] args) {
        int N = 10;
        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = (i+1)*(j+1);
            }
        }
        for (int[] el: arr) {
            System.out.println(Arrays.toString(el));
        }
    }

}
