package java.verifica3;

public class Auto extends VMotore{
    private int porte;
    private String alimentazione;
    public Auto(String marca, String anno, int cilindrata, int porte, String alimentazione) {
        super(marca, anno, cilindrata);
        this.porte= porte;
        this.alimentazione = alimentazione;
    }
    

    public int getPorte() {
      return this.porte;
    }
    public void setPorte(int value) {
      this.porte = value;
    }

    public String getAlimentazione() {
      return this.alimentazione;
    }
    public void setAlimentazione(String value) {
      this.alimentazione = value;
    }

    @Override
    public String toString() {
        return this.getMarca() +", " +this.getAnno() +", "+ this.getCilindrata()+", "+ this.porte + ", "+ this.alimentazione;
    }
}
