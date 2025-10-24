public class Tesi {
    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public Tesi(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "{" +
            " title='" + getTitle() + "'" +
            "}";
    }

}

    
