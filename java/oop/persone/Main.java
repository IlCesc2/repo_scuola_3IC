package oop.persone;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserire Numero Persone:");
        int N = scan.nextInt();

        ElencoStudenti elencoStudenti = new ElencoStudenti(N);
        ElencoDocenti elencoDocenti = new ElencoDocenti(N);
        for (int i = 0; i < N; i++) {
            loop:
            do{
                System.out.println("Inserire tipo di persona valido (docente, studente)");
                String opzione = scan.next();
                switch (opzione) {
                    case "docente":
                        Docente docente = new Docente();
                        docente.createPersona();
                        elencoStudenti.aggiungi(docente);
                        elencoDocenti.aggiungi(docente);
                        break loop;
                    case "studente": 
                        Studente studente = new Studente();
                        studente.createPersona();
                        elencoStudenti.aggiungi(studente);
                        elencoDocenti.aggiungi(studente);
                        break loop;
        
                }
            } while(true);
        }
        elencoDocenti.toScreen();
        elencoStudenti.toScreen();


    }
}
