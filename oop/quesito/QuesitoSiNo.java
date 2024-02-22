package oop.quesito;

import java.util.Scanner;

public class QuesitoSiNo extends Quesito {
    public QuesitoSiNo(String domanda, boolean risposta, int punteggio) {
        super(domanda, risposta ? "Si": "No", punteggio);
    }

    @Override
    public int ask() {
        Scanner scan = new Scanner(System.in);
        String in;
        do {
            System.out.println(this.domanda);
            in = scan.nextLine();
            
        } while(in.compareTo("Si")!=0 && in.compareTo("No")!=0);
        
        if (in.compareTo(this.risposta)==0) return this.punteggio;
        
        return 0;
    }
}
