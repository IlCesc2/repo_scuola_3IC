import java.util.Scanner;

public class cruise {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int part = scan.nextInt();
        int costo = 0;
        int buf = part;
        buf -= buf/15;

        if (part > 40) {
            costo += buf*120;
            System.out.println(costo);
            costo -= costo*10/100;
        } else {
            costo += buf*120;
        }

        int nBuses = part/50 +1;
        int pLiberi = 50*nBuses - part;
        System.out.println(costo);
        System.out.println(nBuses);
        System.out.println(pLiberi);
        
    }
}
