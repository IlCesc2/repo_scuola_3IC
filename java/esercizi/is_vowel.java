import java.util.Scanner;

public class is_vowel {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserire Stringa: ");
        String in = scan.nextLine();
        char[] vowels = {'a','e','i','o','u'};

        StringBuilder sb = new StringBuilder(in);
        int out = 0;
        int index = 0;

        for (char i : in.toCharArray()) {
            for (char j : vowels) {
                if(Character.toLowerCase(i) == j) {
                    out++;
                    sb.deleteCharAt(index);
                    index--;
                }

            }
            index++;
        }
        System.out.println(out);
        System.out.println(sb);
    }
}
