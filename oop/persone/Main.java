package oop.persone;

import java.util.Scanner;

/*
 * Si scriva una classe Persona dotata dei campi
CodiceFiscale, Nome, Cognome e dei metodi per impostare e leggere tali valori. La classe
inoltre dovrà essere dotata del metodo toString(). Si definisca poi una classe Studente come
estensione di Persona; i campi aggiuntivi di Studente rispetto a Persona sono matricola e
università. Inoltre la classe Studente dovrà avere i metodi per impostare e leggere tali campi
e dovrà sovrascrivere il metodo toString(). Si definisca infine la classe Docente che estende
Persona con campi aggiuntivi materia e salario. Anche in questo caso la classe dovrà avere
metodi per leggere e impostare i vari attributi e dovrà sovrascrivere il metodo toString().
Sfruttando le classi precedenti si scriva la classe
ElencoPersone le cui istanze rappresentano elenchi di persone. La classe avrà i seguenti metodi: 
- un costruttore per creare una lista specificando il numero massimo di persone che è
possibile inserire nell'elenco; 
- un metodo aggiungi(Persona p) che aggiunge p all'elenco; 
- un metodo toString() che stampa la lista delle persone nell'elenco. 
Infine realizzare un main che crea una lista di
persone la cui dimensione è scelta dall'utente; chiede poi ripetutamente all'utente di inserire, a
sua scelta, una Persona, uno Studente o un Docente, chiedendo i dati opportuni; infine
stampa la lista delle persone inserite
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserire Numero Persone:");
        int N = scan.nextInt();

        ElencoPersone elencoPersone = new ElencoPersone(N);
        for (int i = 0; i < N; i++) {
            loop:
            do{
                System.out.println("Inserire tipo di persona valido (docente, studente)");
                String opzione = scan.next();
                switch (opzione) {
                    case "docente":
                        Docente docente = new Docente();
                        docente.createPersona();
                        elencoPersone.aggiungi(docente);
                        break loop;
                    case "studente": 
                        Studente studente = new Studente();
                        studente.createPersona();
                        elencoPersone.aggiungi(studente);
                        break loop;
        
                }
            } while(true);
        }
        elencoPersone.toScreen();

    }
}
