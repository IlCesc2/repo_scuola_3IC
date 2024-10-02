import java.util.ArrayList;

public class Manager {
    private ArrayList<Treno> treni = new ArrayList<>();
    private ArrayList<Vagone> parcheggio = new ArrayList<>();

    Manager() {
        treni.add(new Treno(1));
        treni.add(new Treno(0));
    }

    // muovi da convoglio a convoglio un vagone
    // conta numero di passeggeri/ merci su tutti i convogli
    public void moveVagoneFromParcheggio(int ID, Vagone vagone, boolean isInTrain) {
        Treno trenoSel = null;
        for (Treno t : treni) {
            if (t.getID() == ID) {
                trenoSel = t;
                break;
            }
        }
        if (trenoSel == null) return;

        if (isInTrain) {
            Vagone removedVagone = trenoSel.removeVagone(vagone);
            parcheggio.add(removedVagone);
        } else {
            for (Vagone v : parcheggio) {
                if (v.getNext() == vagone) {
                    v.setNext(v.getNext().getNext());
                    v.getNext().getNext().setPrev(v);
                    break;
                }
            }
            trenoSel.addVagone(vagone);
        }
    }

    public int countQt() {
        int out = 0;
        for (Treno treno : treni) {
            out += treno.getTotInside();
        }
        return out;
    }
}
