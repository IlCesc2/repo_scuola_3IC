import java.util.Scanner;

public class next_prime {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            int prime;
            do {
                prime = scan.nextInt();
            } while (prime <0);

            int i = prime;
            int buf =0;

            while(buf != i/2 -1) {
                i++;
                for(int j = 2;  j < i/2; j++) {
                    if(i %j ==0) {
                        break;
                    }
                    buf = j;
                }
            }
            System.out.println(i);
        }
    }
}
