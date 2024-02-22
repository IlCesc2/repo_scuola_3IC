package oop.quesito;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int score =0;
        QuesitoSiNo qSiNo = new QuesitoSiNo("La terra è piatta?", false, 5);
        QuesitoNumerico qNumerico = new QuesitoNumerico("Quante ruote ha un'auto?", 4, 5);
        String[] domanda = {"Qual'è la capitale del Portogallo?", "Lisbona", "Parigi", "Montecarlo", "San Pietroburgo"};
        QuesitoMultiplo qMultiplo = new QuesitoMultiplo(domanda, 1, 5);

        ArrayList<Quesito> quesiti = new ArrayList<Quesito>();
        quesiti.add(qSiNo);
        quesiti.add(qNumerico);
        quesiti.add(qMultiplo);
        int cap = quesiti.size();
        
        for (int i = 0; i < cap; i++) {
            Random random = new Random();
            int randInt= random.nextInt(quesiti.size());
            
            score+= quesiti.get(randInt).ask();
            quesiti.remove(randInt);
        }
        System.out.println("Punteggio Finale: "+ score);
    }
}
