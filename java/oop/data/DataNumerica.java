public class DataNumerica extends DataFormattata{
    public DataNumerica(int giorno,int mese,int anno){
        super(giorno,mese,anno);
    }
    public String stringaFormattata() {
        String anno = Integer.toString(this.getAnno());
        if (anno.length()== 2) anno = "20"+anno;
                // gg/mm/aaaa

        return this.getGiorno()+", "+this.getMese()+", "+anno;
    }
}