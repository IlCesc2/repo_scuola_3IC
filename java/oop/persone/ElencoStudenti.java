package oop.persone;

public class ElencoStudenti extends ElencoPersone {
    public ElencoStudenti(int N) {
        super(N);

    }

    public int conta() {
        int counter =0;
        for (Persona p : this.getPersone()) 
            if (p.getClass() == Studente.class) counter++;
        
        return counter;
    }
    @Override
    public void toScreen() {
        for (Persona p : this.getPersone()) 
            if (p.getClass() == Studente.class) System.out.println(p.getNome());
    }
    
}
