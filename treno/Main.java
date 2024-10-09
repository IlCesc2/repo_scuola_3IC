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

        Vagone test =new VagoneMerci(); 
        Vagone test2 =new VagonePasseggeri(); 
        Vagone test3 =new VagoneMerci(); 
        m.getTrain(0).addVagone(test);
        m.getTrain(0).addVagone(test2);
        m.getTrain(1).addVagone(test3);

        while (option != 0) {
            System.out.println("Inserire opzione");
            option = scan.nextInt();
            System.out.println("Inserire train ID");
            int trainID = scan.nextInt();

            switch (option) {
                case 1:
                    // aggiungi VagoneMerci

                    VagoneMerci vM = new VagoneMerci();
                    m.getTrain(trainID).addVagone(vM);
                    break;
                case 2:
                    // aggiungi VagonePasseggeri
                    VagonePasseggeri vP = new VagonePasseggeri();
                    m.getTrain(trainID).addVagone(vP);

                    break;
                case 3:
                    // sposta da convoglio -> vagone

                    m.getTrain(0).printTrain();
                    m.getTrain(1).printTrain();
                    m.moveVagone(0, 1, test2);
                    m.getTrain(0).printTrain();
                    m.getTrain(1).printTrain();

                    break;
                case 4:
                    // sposta da parcheggio -> convoglio (e viceversa)
                    m.moveVagoneFromParcheggio(trainID, test, true);
                   

                    break;
                case 5:

                    m.getTrain(trainID).printTrain();

                    break;
                case 6:
                    // conta passeggeri + merci sul convoglio
                    System.out.println("TOT: " + m.countPpl(trainID));
                    break;
                default:
                    break;
            }
        }
    }

}