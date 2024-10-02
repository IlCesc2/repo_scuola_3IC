import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Manager m = new Manager();
        /*
         * - aggiungi vagone (di qualsiasi tipo)
         * - rimuovi vagone
         * - sposta un vagone da un convoglio ad un altro
         * - sposta un vagone dal parcheggio ad un convoglio e viceversa
         * - stampa convoglio
         * - contare il numero di passeggeri e merci presenti su ogni convoglio.
         */

        Scanner scan = new Scanner(System.in);
        int option = -1;
        while (option != 0) {
            System.out.println("Inserire opzione");
            option = scan.nextInt();
            switch (option) {
                case 1:
                    // aggiungi
                    break;
                case 2:
                    // rimuovi
                    break;
                case 3:
                    // sposta da convoglio -> vagone
                    break;
                case 4:
                    // sposta da parcheggio -> convoglio (e viceversa)
                    break;
                case 5:
                    // stampa
                    break;
                case 6:
                    // conta passeggeri + merci sul convoglio
                    break;
                default:
                    break;
            }
        }
    }

}