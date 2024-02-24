package oop.persone;

public class ElencoDocenti extends ElencoPersone{

    public ElencoDocenti(int N) {
        super(N);

    }

    public int conta() {
        int counter =0;
        for (Persona p : this.getPersone()) 
            if (p.getClass() == Docente.class) counter++;
        
        return counter;
    }
    @Override
    public void toScreen() {
        for (Persona p : this.getPersone()) 
            if (p.getClass() == Docente.class) System.out.println(p.getNome());
    }
     
}
