package verifica_1;
import java.util.HashMap;
import java.util.Scanner;

public class verifica2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Lunghezza: ");
        int N = scan.nextInt();
        int[] elements = new int[N];
        
        HashMap<Integer, int[]> differences = new HashMap<Integer, int[]>();
        
        for (int i = 0; i < N; i++) {
            System.out.println("Inserire Elemento: ");
            elements[i] = scan.nextInt();
            if (i>0) {
                int[] c = {elements[i-1],elements[i]};
                differences.put(Math.abs(elements[i-1] - elements[i]),c); // aggiungo elementi alla HashMap come chiave la differenza, e come valore il minuendo e il sottraendo
            }
        }
        double max = Double.NEGATIVE_INFINITY;
        // Trova il max nelle differenze
        for (int i : differences.keySet()) {
            if (max< i) max = i;
        }

        int maxP = (int) max;
        // 8 15 22 123 9 23 45 23 45 2
        System.out.println(maxP + ", " + differences.get(maxP)[0] + ", " + differences.get(maxP)[1]);
    }
     
}