package sorting_algs;
import java.util.Scanner;

public class sel_sort {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Inserire lunghezza array: ");
        int N = scan.nextInt();

        int[] ints = new int[N];

        System.out.println("Inserire elementi: ");
        for(int i = 0; i<N; i++) {
            int inp = scan.nextInt();
            ints[i] = inp;
        }

        int counter = sort(ints);
        
        System.out.println("Elementi:");
        for (int i = 0; i < N; i++) {
            System.out.println(ints[i]);
        }
        System.out.println("Iterazione:");
        System.out.println(counter);
    }
    
    public static int sort(int[] arr) {
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[min] > arr[j]) min = j;
                counter++;
            }
            if (min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
            counter++;
        }
        return counter;
    }
}
