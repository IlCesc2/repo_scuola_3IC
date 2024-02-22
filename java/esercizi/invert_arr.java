import java.util.Scanner;

public class invert_arr {
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

        int buf;
        for(int i = 0; i<N/2; i++) {
            buf = in[i];
            in[i] = in[(N-1)-i];
            in[(N-1)-i] = buf;
        }

        for(int i = 0; i<N; i++) {
            System.out.print(in[i] + " ");
        }   
     }
}
