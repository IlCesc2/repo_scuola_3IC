package oop.persone;

public class ElencoPersone {

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

    public Persona[] getPersone() {
      return this.persone;
    }
    
}
