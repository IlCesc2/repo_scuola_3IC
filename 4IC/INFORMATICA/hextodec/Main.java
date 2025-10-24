import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Scrivi il programma Java che utilizzando uno struttura di dati di tipo "pila"
        // (stack) permette di convertire una stringa contenente un numero
        // in formato esadecimale nel controvalore decimale.

        Scanner scan = new Scanner(System.in);
        System.out.println("Inserire Numero Hex: ");
        String p = scan.next();

        System.out.println("Valore Decimale: " + converter(p));

    }

    public static int converter(String hex) {
        Stack stack = new Stack();
        char[] hexArr = hex.toCharArray();
        int dec = 0;

        for (char c : hexArr) {
            stack.push(c);
        }

        int len = stack.getLen();
        for (int i = 0; i < len; i++) {

            char current = stack.peek();
            int asciiCurrent = (int) current;
            int pAsciiCurrent = asciiCurrent >= 65 ? asciiCurrent - 55 : asciiCurrent - 48;

            dec += (Math.pow(16, i) * pAsciiCurrent);
            stack.pop();
        }
        return dec;
    }
}
