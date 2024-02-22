import java.util.Scanner;

public class min_max {
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
        int min=0, max=0;
        int minI= 0, maxI= 0;
        for(int i = 0; i<N; i++) {
            if(in[i] <min) {
                min = in[i];
                minI= i;
            } else{
                max = in[i];
                maxI= i;
            }
        
        }
        System.out.println("Min: "+ minI);
        System.out.println("Max: "+ maxI);

    }
}
