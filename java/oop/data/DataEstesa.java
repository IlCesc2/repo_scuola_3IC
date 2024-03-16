public class DataEstesa extends DataFormattata{

    public DataEstesa(int giorno,int mese,int anno){

        super(giorno,mese,anno);
    }
    String[] mesi = {"gennaio", "febbraio", "marzo", "aprile", "maggio", "giugno", "luglio", "agosto", "settembre", "ottobre", "novembre","dicembre"};
    public String stringaFormattata() {
        String anno = Integer.toString(this.getAnno());
        if (anno.length()== 2) anno = "20"+anno;
        // gg/mese/aaaa

        return this.getGiorno()+", "+this.mesi[this.getMese()-1]+", "+anno;
    }
}