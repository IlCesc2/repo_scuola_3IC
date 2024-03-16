public abstract class DataFormattata {
    protected int giorno;
    protected int mese;
    protected int anno;
    public DataFormattata(int giorno,int mese,int anno){
        this.giorno=giorno;
        this.mese=mese;
        this.anno=anno;
    }

    public int getGiorno() {
        return giorno;
    }

    public int getAnno() {
        return anno;
    }

    public int getMese() {
        return mese;
    }

    public abstract String stringaFormattata();
}