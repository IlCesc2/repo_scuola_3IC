import java.util.regex.*;

public class test {
    public static void main(String[] args) {
        


        String input = "ThisABisABsampleAB";

        String key = "(?=AB)|(?<=AB)"; // Replace this with your desired key

        // Create a regular expression pattern for the key string

        // Use the pattern to split the string and keep the key
        String[] result = input.split(String.format("(?=%1$s)", "AB"));

        // Print the elements in the array
        for (String string : result) {
            System.out.println(string);
        }
    }
    
}
