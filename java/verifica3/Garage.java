package java.verifica3;

public class Garage {
    private VMotore[] veicoli = new VMotore[15];
    private int lastIndex = 0;

    public void aggiungiVeicolo(VMotore nuovoVeicolo) {
        boolean valid = false;
        
        for(int i=0; i< veicoli.length; i++) {
            if (veicoli[i] == null) {
                veicoli[i] = nuovoVeicolo;
                valid = true;
                if (i == lastIndex) lastIndex++;
                break;
            }
        }
        if (!valid) System.out.println("Garage Pieno");

    }
    public VMotore estrazione(int i) {
        if (i< lastIndex) {
            VMotore inst = veicoli[i];
            veicoli[i] = null;
            return inst;
        }
        return null;
        
    }

    public void mostraGarage() {
        for (int i = 0; i < lastIndex; i++) {
            System.out.println(i+") "+ veicoli[i].toString());
        }
    }

    public int getLastIndex() {
      return this.lastIndex;
    }
    public void setLastIndex(int value) {
      this.lastIndex = value;
    }
}
