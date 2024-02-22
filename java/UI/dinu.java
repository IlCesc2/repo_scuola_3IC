package UI;

import static zuclib.GraficaSemplice.*;

import java.util.Scanner;

import zuclib.Tartaruga;

public class dinu {
    public static void main(String[] args) {
        Tartaruga tarta = new Tartaruga();
        //arco(double x, double y, double r, double angolo1, double angolo2)
 
        /*
         * Scanner scan = new Scanner(System.in);

        System.out.println("Numero:");
        int base = scan.nextInt();
         */
        int base = 9;
        //arco(0.5, 0.5, 0.01, 180, 360);
        int start=0, end= 90;
        for (int i = 1; i <= base; i++) {
            double f = fibonacci(i);
            /*
            if (end == 360) {
                start = 0;
                end = 90;
            }

            System.out.println(2*f*Math.cos(i)/0.01+" "+ 2*f*Math.sin(i)/0.01);
            arco(2*f*Math.cos(i)/0.01, 2*f*Math.sin(i)/0.01, 0.01*f, start, end);
            start += 90;
            end += 90;
            */
            double dist = (f+1.6180339887)*0.01;
            tarta.avanti(dist);
            tarta.destra(90);
            tarta.avanti(dist);
            tarta.destra(90);
            tarta.avanti(dist);
            tarta.destra(90);
            tarta.avanti(dist);
            tarta.destra(90);
            tarta.avanti(dist);
            tarta.destra(90);
        }
    }
    public static int fibonacci(int n) {
        if (n<2) return n;
        return fibonacci(n-1) + fibonacci(n-2);
    }
}
