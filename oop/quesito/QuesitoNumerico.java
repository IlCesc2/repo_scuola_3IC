package oop.quesito;

import java.util.Scanner;

public class QuesitoNumerico extends Quesito {
    public QuesitoNumerico(String domanda, int risposta, int punteggio) {
        super(domanda, risposta +"", punteggio);
    }

    @Override
    public int ask() {
        Scanner scan = new Scanner(System.in);
        int in;
        while(true){
            try {
                System.out.println(this.domanda);
                in= scan.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Inserire Valore Valido");
                scan.nextLine();
            } 
        }
        
        if (in == Integer.parseInt(risposta)) return this.punteggio;
        
        return 0;
    }
}
