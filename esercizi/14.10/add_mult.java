import java.util.Scanner;

public class add_mult {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n1 = scan.nextInt();
        int n2 = scan.nextInt();
        int tot = 0;
        
        while (n2>0) {
            tot += n1;
            n2--;
        }

        System.out.println(tot);
        
    }
}