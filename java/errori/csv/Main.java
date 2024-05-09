package csv;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * asdasdasdasdasdadasdasddasdasdasdsa
 * */
public class Main {
    static String PATH = "csv\\data.csv";
    public static void main(String[] args) {

        ArrayList<player> players= new ArrayList<player>();

        player totti = new player("Totti", true, 334 ,9);
        player zanetti = new player("Zanetti", true, 21 ,5);
        player delPiero = new player("Del Piero", true, 56 ,66);

        players.add(totti);
        players.add(zanetti);
        players.add(delPiero);


        try {
            File csvFile = new File(PATH);

            if (csvFile.isFile()) {
                FileWriter csvWriter = new FileWriter(PATH);
                csvWriter.append("Nome, isCapitano, goals, goalsNazionale\n");
                for (player player : players) {
                    String row = player.toString();
                    csvWriter.append(row +"\n");
                }
                csvWriter.close();
            }

        } catch (Exception e) {
            System.out.println("Errore");
        }

    }
}
