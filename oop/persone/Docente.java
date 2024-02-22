package oop.persone;

import java.util.Scanner;

public class Docente extends Persona {
    private String materia;
    private double salario;

    public Docente(String codiceFiscale, String nome,String cognome,String materia, double salario) {
        super(codiceFiscale, nome, cognome);
        this.materia= materia;
        this.salario= salario;

    }
    public Docente() {}
    @Override
    public void toScreen() {
        System.out.println(super.getCodiceFiscale());
        System.out.println(super.getNome());
        System.out.println(super.getCognome());
        System.out.println(this.materia);
        System.out.println(this.salario);
    }
    @Override
    public void createPersona() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserire Nome:");
        setNome(scan.next());
        System.out.println("Inserire Cognome:");
        setCognome(scan.next());
        System.out.println("Inserire Codice Fiscale:");
        setCodiceFiscale(scan.next());
        System.out.println("Inserire Materia:");
        setMateria(scan.next());
        System.out.println("Inserire Salario:");
        setSalario(scan.nextInt());
    }

    public String getMateria() {
      return this.materia;
    }
    public void setMateria(String value) {
      this.materia = value;
    }

    public double getSalario() {
      return this.salario;
    }
    public void setSalario(double value) {
      this.salario = value;
    }
}
