package oop.persone;

import java.util.Scanner;

public class Persona {
    //CodiceFiscale, Nome, Cognome e dei metodi per impostare e leggere tali valori. La classe inoltre dovrà essere dotata del metodo toString()
    private String codiceFiscale;
    private String nome;
    private String cognome;
    public Persona(String codiceFiscale, String nome,String cognome) {
        this.codiceFiscale= codiceFiscale;
        this.nome= nome;
        this.cognome= cognome;
    
    }
    public Persona() {}

    public String getCodiceFiscale() {
        return this.codiceFiscale;
    }
    public String getNome() {
        return this.nome;
    }
    public String getCognome() {
        return this.cognome;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void toScreen() {
        System.out.println(this.codiceFiscale);
        System.out.println(this.nome);
        System.out.println(this.cognome);
    }
    public void createPersona() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserire Nome:");
        setNome(scan.next());
        System.out.println("Inserire Cognome:");
        setCognome(scan.next());
        System.out.println("Inserire Codice Fiscale:");
        setCodiceFiscale(scan.next());
    }

}
