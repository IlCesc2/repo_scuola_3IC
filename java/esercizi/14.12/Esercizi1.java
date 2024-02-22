import java.lang.reflect.Array;
import java.util.Arrays;

public class Esercizi1 {
    public static void main(String[] args) {

        int N = 5;
        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) arr[i][i] = 1;
                else arr[i][j] = 0;

            }
        }

        for (int[] el: arr) {
            System.out.println(Arrays.toString(el));
        }
    }
}
