package java.verifica3;

public class Moto extends VMotore {
    private int tempi;
    private double peso;
    public Moto(String marca, String anno, int cilindrata, int tempi, double peso) {
        super(marca, anno, cilindrata);
        this.tempi= tempi;
        this.peso= peso;
    }

    public int getTempi() {
      return this.tempi;
    }
    public void setTempi(int value) {
      this.tempi = value;
    }

    public double getPeso() {
      return this.peso;
    }
    public void setPeso(double value) {
      this.peso = value;
    }

    @Override
    public String toString() {
        return this.getMarca() +", " +this.getAnno() +", "+ this.getCilindrata()+", "+ this.tempi + ", " + this.peso;
    }
}
