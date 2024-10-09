import java.util.HashMap;
import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserire Stringa: ");
        String p = scan.next();

        System.out.println(checker(p) ? "Stringa Valida":"Stringa Invalida");

    }
    public static boolean checker(String p) {
        HashMap<Character, Character> check = new HashMap<>();
        check.put(')', '(');
        check.put(']', '[');
        check.put('}', '{');
        Stack stack = new Stack();

        for (char c : p.toCharArray()) {
            Character val = check.get(c);
            if (val== null) stack.push(c);
            else if (val== stack.peek()) stack.pop();
            else return false;
        }
        return stack.getLen() == 0;
    }
}