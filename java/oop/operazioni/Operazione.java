package oop.operazioni;

import java.util.ArrayList;
import java.util.List;

public class Operazione {
    List<Float> valori = new ArrayList<Float>();

    public Operazione(double[] val) {
        parsaOperandi(val);
    }

    public void parsaOperandi(double[] val) {
        for (int i = 0; i < val.length; i++) {
            valori.add((float) val[i]);
        }
    }
    public double calcolaRisultato() { return 0; }
}
