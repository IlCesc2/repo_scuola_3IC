public class LibriCat extends Libro {

    public LibriCat(String titolo, int anno, double costo) {
        super(titolo, anno, costo);
    }

    @Override
    public boolean stessocosto(Libro X) {
        return X.costo == this.costo;
    }
    
}
