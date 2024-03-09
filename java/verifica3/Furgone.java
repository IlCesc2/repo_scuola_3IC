package java.verifica3;

public class Furgone extends VMotore{
    private int capacità;
    public Furgone(String marca, String anno, int cilindrata, int capacità) {
        super(marca, anno, cilindrata);
        this.capacità= capacità;
    }

    public int getCapacità() {
      return this.capacità;
    }
    public void setCapacità(int value) {
      this.capacità = value;
    }

    @Override
    public String toString() {
        return this.getMarca() +", " +this.getAnno() +", "+ this.getCilindrata()+", "+ this.capacità;
    }
}
