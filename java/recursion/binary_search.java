package recursion;

import java.util.Random;
import java.util.Scanner;

public class binary_search {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int a[] = new int[10];
        Random rnd = new Random();
        int upperbound = 100;
        a[0] = rnd.nextInt(50);
        for (int i = 1; i < 10; i++){
                a[i] = rnd.nextInt((upperbound*i)-a[i-1]) + a[i-1];
                System.out.println(a[i]);
        }
        
        System.out.println("Inserire valore da cercare: ");
        int in = scan.nextInt();

        System.out.println(bSearch(a, in, 0,a.length));

    }
    public static boolean bSearch(int[] arr, int target, int min, int max) {
        int mid = (max+min)/2;
        if (max - min== 1 ) return false;
    
        if (arr[mid] > target) 
            return bSearch(arr, target, min, mid);
        else if (arr[mid] < target) 
            return bSearch(arr, target, mid, max);
    
        return true;   
    }
}