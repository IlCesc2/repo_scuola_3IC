package recursion;

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
                    System.out.println(sommaArray(vect, 0));
                    break;
                case 2:
                    System.out.println(maxArray(vect, 0));
                    break;
                case 3:
                    System.out.println(minArray(vect, 0));
                    break;

                case 4:
                    System.out.println("Inserire valore: ");
                    int in = scan.nextInt();
                    System.out.println(valueOccurenceArray(vect, 0, in));
                    break;
                    
                case 5:
                    System.out.println(isPalindrome(vect, 0));
                    break;

                default: break;
            }
        }
    }
    /*
    public static int sommaArray (int [] vet, int index): somma valori di un array 
    public static int maxArray (int [] vet, int index): trova il valore più grande tra gli elementi di un array
    public static int minArray (int [] vet, int index): trova il più piccolo tra i valori di un array
    public static int valueOccurenceArray (int [] vet, int index): conta quante volte un valore è presente tra gli elementi di un array
    public static boolen isPalindrome (int [] vet, int index): somma valori di un array
     */

    public static int sommaArray (int [] vet, int index) {
        if (index == vet.length) return 0;
        

        return vet[index]+sommaArray(vet, index+1);
    }

    public static int maxArray (int [] vet, int index) {
        if (index == vet.length) return Integer.MIN_VALUE;
        
        int a = maxArray(vet, index+1);

        if (vet[index]>a) return vet[index];
        
        return a;
    }

    public static int minArray (int [] vet, int index) {
        if (index == vet.length) return Integer.MAX_VALUE;
        
        int a = minArray(vet, index+1);

        if (vet[index]<a) return vet[index];
        
        return a;
    }

    public static int valueOccurenceArray (int [] vet, int index, int target) {
        if (index == vet.length) return 0;
        
        int out = vet[index] == target ? 1+ valueOccurenceArray(vet, index+1, target) : valueOccurenceArray(vet, index+1, target);
        
        return out;
    }
    public static boolean isPalindrome (int [] vet, int index) {
        if (index == vet.length) return true;
        
        if (vet[index]== vet[vet.length-1-index] ) return isPalindrome(vet, index+1);
        
        return false;
    }
    
}