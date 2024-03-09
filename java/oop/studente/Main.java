package java.oop.studente;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Studente[] studenti = new Studente[10];
        VotoVerifica[] voti = new VotoVerifica[10];
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < studenti.length; i++) {
            String nome = scan.nextLine();
            String cognome = scan.nextLine();
            int eta = scan.nextInt();
            studenti[i] = new Studente(nome, cognome, eta);
        }
        for (int i = 0; i < studenti.length; i++) {
            double votoTeorico = scan.nextDouble();
            double votoPratico = scan.nextDouble();
     
            voti[i] = new VotoVerifica(votoTeorico, votoPratico);
        }
        Arrays.sort(studenti);
        Arrays.sort(voti);
        System.out.println(Arrays.toString(studenti));
        System.out.println(Arrays.toString(voti));

    }
}
