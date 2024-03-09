package java.verifica3;

public class VMotore {
    private String marca;
    private String anno;
    private int cilindrata;

    public VMotore(String marca, String anno, int cilindrata) {
        this.marca = marca;
        this.anno = anno;
        this.cilindrata = cilindrata;
    }

    public String getMarca() {
      return this.marca;
    }
    public void setMarca(String value) {
      this.marca = value;
    }

    public String getAnno() {
      return this.anno;
    }
    public void setAnno(String value) {
      this.anno = value;
    }

    public int getCilindrata() {
      return this.cilindrata;
    }
    public void setCilindrata(int value) {
      this.cilindrata = value;
    }

    @Override
    public String toString() {
        return this.marca +", " +this.anno +", "+ this.cilindrata;
    }
}
