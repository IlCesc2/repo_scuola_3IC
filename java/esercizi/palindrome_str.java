import java.util.Scanner;

public class palindrome_str {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserire Stringa: ");
        String in = scan.nextLine();
        for(int i = 0; i< in.length()-1; i++) {
            if (in.charAt(i) != in.charAt(in.length()-1-i)) {
                System.out.println("Non è palindromo");
                return;
            }
        }
        System.out.println("E' palindromo");

    }
}
