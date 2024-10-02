public class Studente {
    private String nome;
    private Tesi tesi;

    public Studente(String nome, Tesi tesi) {
        this.nome = nome;
        this.tesi = tesi;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tesi getTesi() {
        return this.tesi;
    }

    public void setTesi(Tesi tesi) {
        this.tesi = tesi;
    }


    @Override
    public String toString() {
        return "{" +
            " nome='" + getNome() + "'" +
            ", tesi='" + getTesi() + "'" +
            "}";
    }


}
