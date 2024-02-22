import java.util.Scanner;
public class secret_num_user_input {
    public static void main(String[] args) {
       System.out.println("Il tuo numero è: "+ calc());
    }

    public static int calc() {
        try (Scanner scan = new Scanner(System.in)) {
            int max = 100, min = 1;

            int guess = (max-min)/2;
            int inp;

            do {
                System.out.println("Inserire numero > di "+ min + " e < di "+ max);
                inp = scan.nextInt();
            } while (!(min<inp && inp<max));

            int i = 0;
            do {
                System.out.println("Il tuo numero è =, > o < di " +guess + "?");
                String sign = scan.next();

                if (sign.equals(">")) {
                    min = guess;
                } else if (sign.equals("<")) {
                    max = guess;
                } else if (sign.equals("=")) {
                    break;
                } else {
                    continue;
                }
                
                guess = min+(max-min)/2;
                i++;
            } while(true);
            System.out.println("Iterazioni: "+i);
            return guess;
        }
    }
}