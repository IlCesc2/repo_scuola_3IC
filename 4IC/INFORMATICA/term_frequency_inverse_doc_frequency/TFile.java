public class TFile {
    private int lenght;
    private String name;

    public TFile(int lenght, String name, String[] terms) {
        this.lenght = lenght;
        this.name = name;
        this.terms = terms;
    }
    private String[] terms;

    public String getName() {
        return this.name;
    }

    public String[] getTerms() {
        return this.terms;
    }

}
