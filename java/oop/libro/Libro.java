public abstract class Libro {
    String titolo;
    int anno;
    double costo;

    public Libro(String titolo, int anno, double costo) {
        this.titolo = titolo;
        this.anno = anno;
        this.costo = costo;
    }

    public abstract boolean stessocosto(Libro X);
}
