package oop.players;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        player totti = new player();
        totti.setName("Totti");
        totti.setCapitano(true);
        totti.setGoals(334);
        totti.setGoalNazionale(9);
        totti.printData();
        
        player zanetti = new player("Zanetti", true, 21 ,5);
        zanetti.printData();
        
        player delPiero = new player();
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserire rispettivamente: Nome, isCapitano, Goal in club, goal in nazionale");
        delPiero.setName(scan.nextLine());
        delPiero.setCapitano(scan.nextBoolean());
        delPiero.setGoals(scan.nextInt());
        delPiero.setGoalNazionale(scan.nextInt());
        delPiero.printData();
    }

}
