import java.text.DecimalFormat;
import java.util.Scanner;

public class port {
    public static void main(String[] args) {
        final DecimalFormat df = new DecimalFormat("0.00");

        double ster = 2.52;
        double dol =1.514;

        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("inserire £");
            double in1 = scan.nextDouble();
            System.out.println("inserire $");
            double in2 = scan.nextDouble();

            double out = (in1*ster)+(in2*dol);
            System.out.println("Euri: " + df.format(out));
        }
    }
}
