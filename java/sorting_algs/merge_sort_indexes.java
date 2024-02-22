package sorting_algs;
import java.util.Arrays;

public class merge_sort_indexes {
    public static void main(String[] args) {
        int[] arr = {5,4,1,7,9,10,11};

        System.out.println("Prima del Sorting: "+ Arrays.toString(arr));
        mergeSort(arr);
        System.out.println("Dopo il Sorting: "+ Arrays.toString(arr));
    }
    public static void mergeSort(int[] arr, int a, int b) {
        if (arr.length == 1) return;
        
        
        mergeSort(a);
        mergeSort(b);
        int[] o = merge(a, b);
        for (int i = 0; i < o.length; i++) arr[i] = o[i];
    }

    public static int[] merge(int[] arr, int a, int b) {       
        int[] out = new int[a.length+b.length];
        int i=0 , ia = 0, ib=0;
        while (ia< a.length && ib< b.length) {
            if (a[ia]<b[ib]) {
                out[i] = a[ia];
                ia++;                
            } else{
                out[i] = b[ib];
                ib++;
            }
            i++;
        }
        while (ia < a.length) {
            out[i] = a[ia];
            i++;
            ia++;
        }
        while (ib< b.length) {
            out[i] = b[ib];
            i++;
            ib++;
        }
        return out;
    }
}