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

    public void setName(String name) {
        this.name = name;
    }

    public int getLenght() {
        return this.lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public String[] getTerms() {
        return this.terms;
    }

    public void setTerms(String[] terms) {
        this.terms = terms;
    }
}
