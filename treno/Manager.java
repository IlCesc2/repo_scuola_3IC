import java.util.ArrayList;

public class Manager {
    private ArrayList<Treno> treni = new ArrayList<>();
    private ArrayList<Vagone> parcheggio = new ArrayList<>();

    Manager() {
        treni.add(new Treno(1));
        treni.add(new Treno(0));
    }

    public Treno getTrain(int ID) {
        Treno trenoSel = null;
        for (Treno t : treni) {
            if (t.getID() == ID) {
                trenoSel = t;
                break;
            }
        }
        if (trenoSel == null) System.out.println("No Train found with given ID: "+ ID);
        return trenoSel;
    }

    // muovi da convoglio a convoglio un vagone
    public void moveVagone(int IDStart, int IDTarget, Vagone vagone) {
        Treno trenoStart = null;
        for (Treno t : treni) {
            if (t.getID() == IDStart) {
                trenoStart = t;
                break;
            }
        }
        if (trenoStart == null) return;
        Treno trenoSel = null;
        for (Treno t : treni) {
            if (t.getID() == IDTarget) {
                trenoSel = t;
                break;
            }
        }
        if (trenoSel == null) return;
        

        trenoStart.removeVagone(vagone);
        trenoSel.addVagone(vagone);
    }

    // conta numero di passeggeri/ merci su tutti i convogli
    public int countPpl(int i) {
        if (i == treni.size()-1) return treni.get(i).getTotInside();
        return treni.get(i).getTotInside() + countPpl(i+1);
    }


    public void moveVagoneFromParcheggio(int ID, Vagone vagone, boolean isInTrain) {
        Treno trenoSel = null;
        for (Treno t : treni) {
            if (t.getID() == ID) {
                trenoSel = t;
                break;
            }
        }
        if (trenoSel == null)
            return;

        if (isInTrain) {
            Vagone removedVagone = trenoSel.removeVagone(vagone);
            parcheggio.add(removedVagone);
        } else {
            // rimuove dal parcheggio
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

 
}
