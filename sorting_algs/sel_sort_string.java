package sorting_algs;
import java.util.Scanner;

public class sel_sort_string {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int option= 0;
        do {
            System.out.println("Interi o Stringhe? (0 o 1): ");
            option = scan.nextInt();
        } while (option != 0 && option != 1);

        System.out.println("Inserire lunghezza array: ");
        int N = scan.nextInt();

        int[] ints = new int[N];
        String[] strings = new String[N];

        System.out.println("Inserire elementi: ");
        for(int i = 0; i<N; i++) {
            if (option == 0) {
                int inp = scan.nextInt();
                ints[i] = inp;
            } else {
                String inp = scan.next();
                strings[i] = inp;
            }
        }

        if(option == 0) sort(ints);
        else sort(strings);
        
        System.out.println("Elementi:");
        for (int i = 0; i < N; i++) {
            if (option == 0) System.out.println(ints[i]);
            else System.out.println(strings[i]);
        }
    }
    public static void sort(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[min].compareTo(arr[j])>0) min = j;
            }
            if (min != i) {
                String temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[min] > arr[j]) min = j;
            }
            if (min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }
}
