import java.util.Scanner;

public class sub_division {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n1 = scan.nextInt();
        int n2 = scan.nextInt();
        
        int tot = 0;

        while (n1>=n2) {
            n1 -= n2;
            tot++;
        }

        System.out.println(tot);
        System.out.println(n1);
    }
}