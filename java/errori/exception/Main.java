package exception;
import java.util.Random;
import java.util.Scanner;
/**
 * ecsepscion
 * */
public class Main {
    public static void main(String[] args) {
        int[] values = new int[30];
       Scanner scan = new Scanner(System.in);

        int choice = -1;
        while (choice!= 0) {
            System.out.println("Inserire Opzione:\n1->setupArray\n2->sortArray\n3->recursiveSum");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Inserire Massimo valore");
                    int max = scan.nextInt();
                    System.out.println("Inserire Minimo valore");
                    int min = scan.nextInt();
                    setupArray(values, max, min);
                    break;
                case 2:
                    arraySort(values);
                    break;
                case 3: 
                    recurSum(values, 0);
                    break;
            
                default:
                    System.out.println("Selezionare un opzione valida");
                    break;
            }
        }
    }

    static void setupArray(int[] vect, int max, int min) {
        if (min>= max) {
            System.out.println("Errore, minore >= a max");
            return;
        }
        int val = min-1;
        Random rand = new Random();
        for (int i = 0; i < vect.length; i++) {
            
            while (val>max || val< min) {
                val = rand.nextInt((max - min) + 1) + min;
            }
            vect[i] = val;
            val = min-1;
        }
    }

    static void arraySort(int[] vect) {
        Boolean hasSwapped = true;
        while (hasSwapped) {
            hasSwapped = false;
            for (int i = 0; i < vect.length-1; i++) {
                if (vect[i] > vect[i+1]) {
                    int temp = vect[i];
                    vect[i] = vect[i+1];
                    vect[i+1] = temp;
                    hasSwapped = true;
                }
            }
        }
    }
    public static int recurSum(int[] vect, int i) {
        if(i== vect.length-1) return vect[i];
        i++;
        return vect[i]+ recurSum(vect, i);
    }

}
