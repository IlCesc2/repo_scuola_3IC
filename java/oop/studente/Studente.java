package java.oop.studente;

public class Studente implements Comparable<Studente> {

    private String nome;
    private String cognome;
    private int eta;

    public Studente(String nome, String cognome, int eta) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
    }

    @Override
    public int compareTo(Studente studente) {
        return studente.getEta()- getEta();
        
    }

  

    public String getNome() {
      return this.nome;
    }
    public void setNome(String value) {
      this.nome = value;
    }

    public String getCognome() {
      return this.cognome;
    }
    public void setCognome(String value) {
      this.cognome = value;
    }

    public int getEta() {
      return this.eta;
    }
    public void setEta(int value) {
      this.eta = value;
    }
}
