import java.util.Scanner;

public class linear_search {
    public static void main(String[] args) {
        System.out.println(gino());
    }
    public static int gino() {
        final int N = 10;
        Scanner scan = new Scanner(System.in);

        int[] in = new int[N];
        for(int i = 0; i<N; i++) {
            int inp = scan.nextInt();
            in[i] = inp;
        }

        int target = scan.nextInt();

        for(int i = 0; i<N; i++) {
            if(in[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
