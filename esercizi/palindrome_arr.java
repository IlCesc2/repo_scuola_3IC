import java.util.Scanner;

public class palindrome_arr {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserire lunghezza array: ");
        int N = scan.nextInt();

        int[] in = new int[N];

        System.out.println("Inserire elementi: ");
        for(int i = 0; i<N; i++) {
            int inp = scan.nextInt();
            in[i] = inp;
        }

        for(int i = 0; i< in.length-1; i++) {
            if (in[i] != in[N-1-i]) {
                System.out.println("Non è palindromo");
                return;
            }
        }
        System.out.println("E' palindromo");

        
    }
}
