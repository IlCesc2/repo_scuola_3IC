package oop.persone;

import java.util.Scanner;

public class Studente extends Persona {
    //matricola e università
    private int matricola;
    private String università;
    public Studente(String codiceFiscale, String nome,String cognome,int matricola, String università) {
        super(codiceFiscale, nome, cognome);
        this.matricola= matricola;
        this.università= università;

    }
    public Studente() {}
    @Override
    public void toScreen() {
        System.out.println(super.getCodiceFiscale());
        System.out.println(super.getNome());
        System.out.println(super.getCognome());
        System.out.println(this.matricola);
        System.out.println(this.università);
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
        System.out.println("Inserire Matricola:");
        setMatricola(scan.nextInt());
        System.out.println("Inserire Università:");
        setUniversità(scan.next());
    }


    public int getMatricola() {
      return this.matricola;
    }
    public void setMatricola(int value) {
      this.matricola = value;
    }

    public String getUniversità() {
      return this.università;
    }
    public void setUniversità(String value) {
      this.università = value;
    }
}
