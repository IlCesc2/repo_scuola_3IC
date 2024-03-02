package oop.verifiche;

import java.time.LocalDate;

public class Verifica {
    /*
     * Al fine di digitalizzare il calendario delle verifiche programmate per una certa classe, la segreteria dell'ITIS "Simpatia" incarica la 3IC -Software inc. di progettare opportune classi:
        La classe Verifica prevede come proprietà: data, punteggioMassimo, disciplina;
        La classe VerificaScritta estende Verifica e aggiunge la proprietà durata della prova;
        La classe VerificaOrale estende Verifica e presenta la proprietà nStudentiInterrogati, e numStudentiDellaClasse; 
     */
    private LocalDate data;
    int punteggioMassimo;
    String disciplina;

    public Verifica(LocalDate data, int punteggioMassimo, String disciplina) {
        this.data = data;
        this.punteggioMassimo = punteggioMassimo;
        this.disciplina = disciplina;
    }


    public LocalDate getData() {
      return this.data;
    }
    public void setData(LocalDate value) {
      this.data = value;
    }

    public int getPunteggioMassimo() {
      return this.punteggioMassimo;
    }
    public void setPunteggioMassimo(int value) {
      this.punteggioMassimo = value;
    }

    public String getDisciplina() {
      return this.disciplina;
    }
    public void setDisciplina(String value) {
      this.disciplina = value;
    }
}
