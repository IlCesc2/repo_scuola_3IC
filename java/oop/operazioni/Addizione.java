package oop.operazioni;

public class Addizione extends Operazione {
    public Addizione(double[] val) {
        super(val);
    }

    @Override
    public double calcolaRisultato() {
        double out = super.valori.get(0);
        for (int i = 1; i < super.valori.size(); i++) {
            out += super.valori.get(i);
        }
        return out;
    }
}
