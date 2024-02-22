package oop.quesito;

import java.util.Scanner;

public class Quesito {
    /*
     * Scrivere la classe QuesitoSiNo che estende la classe Quesito in modo da
     * rappresentare domande a
     * cui possa essere risposto solo si o no. Sovrascrivere il metodo ask() in modo
     * da garantire che l’utente
     * risponda si o no (prima che venga restituito il punteggio conseguito).
     * Scrivere la classe QuesitoNumerico che estende la classe Quesito in modo da
     * rappresentare domande
     * a cui possa essere risposto solo con un valore intero. Sovrascrivere il
     * metodo ask() in modo da garantire
     * che l’utente risponda con un valore intero (prima che venga restituito il
     * punteggio conseguito).
     * Scrivere la classe QuesitoMultiplo che estende la classe QuesitoNumerico in
     * modo da rappresentare
     * domande che offrono un certo numero di opzioni (prefissato) e alle quali
     * possa essere risposto solo con un
     * valore intero positivo minore o uguale al numero di opzioni disponibili.
     * Sovrascrivere il metodo ask() in modo da garantire che l’utente risponda con
     * un valore consentito (prima che venga restituito il punteggio
     * conseguito).
     * Per verificare il corretto funzionamento delle classi realizzate scrivere il
     * programma Test che riempie un array con quesiti di diversa natura e poi
     * simula un’interrogazione
     * calcolando il punteggio totale ottenuto. A scelta, l’interrogazione può
     * essere fatta estraendo in modo casuale tre quesiti dall’array.
     * 
     */

    String domanda;
    String risposta;
    int punteggio;

    public Quesito(String domanda, String risposta, int punteggio) {
        this.domanda = domanda;
        this.risposta = risposta;
        this.punteggio = punteggio;

    }
    public int ask() {
        Scanner scan = new Scanner(System.in);
        System.out.print(this.domanda);
        String in = scan.nextLine();
        if (in.compareTo(this.risposta)==0) return this.punteggio;

        return 0;
    }

}
