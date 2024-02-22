import java.util.ArrayList;
import java.util.Scanner;

public class alt_sum_list {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> odd = new ArrayList<Integer>();
        ArrayList<Integer> even = new ArrayList<Integer>();
        
        int oddSum =0, evenSum =0;

        for(int i = 0; i<10; i++) {
            int in = scan.nextInt();
            if (i %2 == 0) {
                odd.add(in);
                oddSum += in;
            } else {
                even.add(in);
                evenSum += in;
            }
        }
        int diff = oddSum >evenSum ?
            oddSum-evenSum :
            evenSum -oddSum;


        for(int i =0; i< even.size(); i++) {
            if (odd.get(i) < diff) { System.out.println(odd.get(i)); }
            if (even.get(i) < diff) { System.out.println(even.get(i)); }
        }
    }
}