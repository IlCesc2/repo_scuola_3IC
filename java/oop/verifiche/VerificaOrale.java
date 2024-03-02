package oop.verifiche;

import java.time.LocalDate;
import java.util.Random;

public class VerificaOrale extends Verifica {
    private int nStudentiInterrogati;
    int numStudentiDellaClasse;
    public VerificaOrale(LocalDate data, int punteggioMassimo, String disciplina, int nStudentiInterrogati, int numStudentiDellaClasse) {
        super(data, punteggioMassimo, disciplina);
        this.nStudentiInterrogati = nStudentiInterrogati;
        this.numStudentiDellaClasse = numStudentiDellaClasse;
    }

    public int getNStudentiInterrogati() {
      return this.nStudentiInterrogati;
    }
    public void setNStudentiInterrogati(int value) {
      this.nStudentiInterrogati = value;
    }

    public int getNumStudentiDellaClasse() {
      return this.numStudentiDellaClasse;
    }
    public void setNumStudentiDellaClasse(int value) {
      this.numStudentiDellaClasse = value;
    }

    public int[] sottoAChiTocca() {
        int[] out = new int[nStudentiInterrogati];
        Random rand = new Random();
        for (int i = 0; i < out.length; i++) {
            boolean isInArr;
            do{
                isInArr= false;
                int random_integer = rand.nextInt(nStudentiInterrogati-1) + 1;
                for (int j : out) {
                    if (j== random_integer) {
                        isInArr=true;
                    }
                }
            } while(isInArr);        
        }
        return out;
    }
    /*
     * 
     */
}
