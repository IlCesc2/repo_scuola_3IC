package sorting_algs;

import java.util.Scanner;

public class shaker_sort {
    
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
            Boolean hasSwapped = true;
            int counter = 0;
            while (hasSwapped) {
                hasSwapped = false;
                for (int i = 0; i < arr.length-1; i++) {
                    if (arr[i] > arr[i+1]) {
                        int temp = arr[i];
                        arr[i] = arr[i+1];
                        arr[i+1] = temp;
                        hasSwapped = true;
                    }
                    counter++;
                }
                for (int i = arr.length-1; i > 0; i--) {
                    if (arr[i] < arr[i-1]) {
                        int temp = arr[i];
                        arr[i] = arr[i-1];
                        arr[i-1] = temp;
                        hasSwapped = true;
                    }
                    counter++;
                }
                counter++;
            }
            return counter;
        }
        
}
