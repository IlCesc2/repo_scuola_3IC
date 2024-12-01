package recursion;

import java.util.Random;
import java.util.Scanner;

public class menu {
    public static void main(String[] args) {
       
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
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                  
                    break;

                case 4:
               
                    break;
                    
                case 5:
                    
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
    public static boolean isPalindrome (int [] vet, int index): somma valori di un array
     */

    public static int sommaArray (int [] vet, int index) {
        if (index == vet.length-1) return vet[index];

        return vet[index]+sommaArray(vet, index+1);// vet[index]+vet[index+1]+vet[index+2]+vet[index+3]+...+vet[lenght-1]+
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