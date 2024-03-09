package oop.operazioni;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListaOperazioni {
    List<Operazione> listaOperazione = new ArrayList<Operazione>();
    public void svuota() { listaOperazione.clear(); }

    public double calcolaSommaTotale() {
        double tot=0;
        for (Operazione operazione : listaOperazione) {
            tot+= operazione.calcolaRisultato();
        }
        return tot;
    }
    public void inserisci(String op) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserire N valori");
        int N = scan.nextInt();
        double[] val = new double[N]; 
        for (int i = 0; i < N; i++) {
            System.out.println("Inserire N valori");
            val[i] = scan.nextDouble();
        }
        switch (op) {
            case "addizione":
                
                listaOperazione.add(new Addizione(val));
                break;
            case "moltiplicazione":
                
                listaOperazione.add(new Moltiplicazione(val));
                break;
            default: break;
        }
    }

    public void leggiFile() {

    }   


}   
