
package oop.quesito;
import java.util.Scanner;

public class QuesitoMultiplo extends Quesito {
    static int max;
    public QuesitoMultiplo(String[] domanda, int risposta, int punteggio) {
        super(parseDomanda(domanda), risposta+"", punteggio);
    }
    public static String parseDomanda(String[] domanda) {
        max = domanda.length-1;
        String out = domanda[0] +"\n";
        for (int i = 1; i < domanda.length; i++) {
            out += i + ") " + domanda[i]+"\n";
        }
        return out;
    }

    @Override
    public int ask() {
        Scanner scan = new Scanner(System.in);
        int in = 0;
        do {
            try {
                System.out.println(this.domanda);
                in= scan.nextInt();
                
            } catch (Exception e) {
                System.out.println("Inserire Opzione Valida");
                scan.nextLine();
            } 
        } while(in > max || in < 1);

        if (in == Integer.parseInt(this.risposta)) return this.punteggio;
        
        return 0;
    }
}
