package oop.persone;

public class ElencoPersone {
    /*
     * ElencoPersone le cui istanze rappresentano elenchi di persone. La classe avrà i seguenti metodi: 
- un costruttore per creare una lista specificando il numero massimo di persone che è
possibile inserire nell'elenco; 
- un metodo aggiungi(Persona p) che aggiunge p all'elenco; 
- un metodo toString() che stampa la lista delle persone nell'elenco. 
     */

    private int N;
    private Persona[] persone;
    private int latestIndex =0;
    public ElencoPersone(int N) {
        this.N= N;
        this.persone= new Persona[this.N];
    }

    public void aggiungi(Persona p) {
        persone[latestIndex] = p;
        latestIndex++;
    }
    public void toScreen(){
        for (Persona p : persone)
            System.out.println(p.getNome());
    }
}
