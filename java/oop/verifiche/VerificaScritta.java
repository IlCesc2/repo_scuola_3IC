package oop.verifiche;

import java.time.LocalDate;

public class VerificaScritta extends Verifica {
    private double durata;
    public VerificaScritta(LocalDate data, int punteggioMassimo, String disciplina, double durata) {
        super(data, punteggioMassimo, disciplina);
        this.durata= durata;
    }

    public double getDurata() {
      return this.durata;
    }
    public void setDurata(double value) {
      this.durata = value;
    }
}
