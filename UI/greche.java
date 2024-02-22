package UI;

import java.util.Scanner;

import zuclib.Tartaruga;

public class greche {
    public static void main(String[] args) {
        Tartaruga tarta = new Tartaruga();
        Scanner scan = new Scanner(System.in);

        int in = scan.nextInt();
        
    }
    public static void greca1(Tartaruga tarta) {
        tarta.pennaSu();
        tarta.gotoXY(0, 0.6);
        tarta.setGrossezza(0.005);
        tarta.destra(90);
        tarta.pennaGiu();
        tarta.avanti(1);
        double distance = 0.03;

        tarta.pennaSu();   
        tarta.gotoXY(0, 0.58);
        tarta.pennaGiu();

        while (tarta.getX() < 1) {
            
            tarta.avanti(distance*2);
            tarta.destra(90);
            tarta.avanti(distance);
            tarta.destra(90);
            tarta.avanti(distance);
            tarta.sinistra(90);
            tarta.avanti(distance);
            tarta.sinistra(90);
            tarta.avanti(distance*3);
            tarta.sinistra(90);
            tarta.avanti(distance);
            tarta.sinistra(90);
            tarta.avanti(distance);
            tarta.destra(90);
            tarta.avanti(distance);
            tarta.destra(90);
            tarta.avanti(distance);
        }
        tarta.pennaSu();
        tarta.gotoXY(0, 0.49);
        tarta.setGrossezza(0.005);
        tarta.setAngolo(360);
        tarta.pennaGiu();
        tarta.avanti(1);

    }
    public static void greca2(Tartaruga tarta) {
        tarta.pennaSu();
        tarta.gotoXY(0, 0.6);
        tarta.setGrossezza(0.005);
        tarta.destra(90);
        tarta.pennaGiu();
        tarta.avanti(1);
        double distance = 0.03;

        tarta.pennaSu();   
        tarta.gotoXY(0, 0.58);
        tarta.pennaGiu();

        while (tarta.getX() < 1) {
            
            tarta.avanti(distance*2);
            tarta.destra(90);
            tarta.avanti(distance);
            tarta.destra(90);
            tarta.avanti(distance);
            tarta.sinistra(90);
            tarta.avanti(distance);
            tarta.sinistra(90);
            tarta.avanti(distance*3);
            tarta.sinistra(90);
            tarta.avanti(distance*2);
            tarta.destra(90);
            
        }
        tarta.pennaSu();
        tarta.gotoXY(0, 0.49);
        tarta.setGrossezza(0.005);
        tarta.setAngolo(360);
        tarta.pennaGiu();
        tarta.avanti(1);
    }
    public static void greca3(Tartaruga tarta) {

    }
    public static void greca4(Tartaruga tarta) {

    }
    
}
