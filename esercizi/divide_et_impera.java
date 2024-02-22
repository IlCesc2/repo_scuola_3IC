import java.util.Random;
import java.util.Scanner;


public class divide_et_impera {
    static int N;
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

        System.out.println(bSearch(a, in));

    }
    public static boolean bSearch(int[] arr, int target) {
        int max = arr.length, min = 0;
        int mid = max/2;
        int prevNum = mid;
        while(true) {
            if(arr[mid] == target) {
                return true;
            } else if (arr[mid] > target) {
                max = mid;
                mid = (max + min) /2;
            } else {
                min = mid;
                mid = (max + min) /2;
            }
            if(prevNum== mid) break;
            prevNum = mid;
        }
        return false;
    }

    
}
