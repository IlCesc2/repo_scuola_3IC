import java.util.Random;
import java.util.Scanner;

public class menu {
    public static void main(String[] args) {
        /*
        - public static int valueIndex (int value, int [ ] vet) il metodo riceve come parametri in ingresso l'array su cui effettuare la ricerca ed l valore da trovare, restituisce un intero corrispondente alla posizione in cui il valore è stato trovato oppure -1 se il valore non è presente
        - public static boolen isInOrder(int [ ] vet) riceve come parametro in ingresso un array e determina se l'array è ordinato in questo caso restituisce true altrimenti false.
        - public static void showArray (int [ ] vet) riceve come parametro in ingresso un array e visualizza il vettore schermo utilizzando il seguente formato 
         */
        int N = 10;
        int[] vect = new int[N];
        Random rand = new Random();
        for (int i = 0; i < vect.length; i++) {
            vect[i] = (int) rand.nextInt(100);
            System.out.println(vect[i]);
        }
        Scanner scan = new Scanner(System.in);
        int option = -1;
        while (option != 0) {
            System.out.println("Inserire opzione");
            option = scan.nextInt();
            switch (option) {
                case 1:
                    System.out.println("inserire valore da cercare");
                    int val = scan.nextInt();
                    int out = valueIndex(val, vect);
                    String index = out == -1 ? "Non è nell'array" : "L'elemento si trova a " + out;
                    System.out.println(index);
                    break;
                case 2:
                    String inOrder = isInOrder(vect) ? "E' in ordine" : "Non è in ordine";
                    System.out.println(inOrder);
                    break;
                case 3:
                    showArray(vect);
                    break;
                default: break;
            }
        }
    }
    public static int valueIndex (int value, int [] vet) {
        for (int i = 0; i < vet.length; i++) {
            if (vet[i]==value) return i;
        }
        return -1;
    }
    public static boolean isInOrder(int [ ] vet) {
        int prevValue = vet[0];
        for (int i = 1; i < vet.length; i++) {
            if (prevValue> vet[i]) return false;
        }
        return true;
    }
    public static void showArray (int [] vet)  {
        for (int i : vet) {
            String flen = "" +i;
            int len = flen.length();
            String middleString = "";
            for (int j = 0; j < len; j++) {
                middleString+="─";
            }
            System.out.print("┌"+ middleString + "┐");
            
        }
        System.out.println();
        for (int i : vet) {
            System.out.print(" "+i+" ");
        }
        System.out.println();
        for (int i : vet) {
            String flen = "" +i;
            int len = flen.length();
            String middleString = "";
            for (int j = 0; j < len; j++) {
                middleString+= "─";
            }

            System.out.print("└" + middleString + "┘");
        }
        System.out.println();
    }
}
