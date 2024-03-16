import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserire Lunghezza");
        int N = scan.nextInt();
        DataFormattata[] date = new DataFormattata[N];

        for (int i = 0; i < N; i++) {
            System.out.println("Inserire valore:1-> Numerica\n2-> Estesa");
            int option= scan.nextInt();
            int anno = scan.nextInt();
            int mese = scan.nextInt();
            int giorno = scan.nextInt();
            switch (option){
                case (1):
                    date[i]= new DataNumerica(anno, mese, giorno);
                    break;
                case (2):
                    date[i]= new DataEstesa(anno, mese, giorno);
                    break;
            }
        }
        for (DataFormattata data:date) {
            System.out.println(data.stringaFormattata());
        }
    }
}