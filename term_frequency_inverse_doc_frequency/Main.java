import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static String DIRECTORY_NAME ="/documenti/";
    public static void main(String[] args) {
        /*
         * 
         * 
         * 
         */

        Set<String> fileNames = listFilesUsingJavaIO("documenti");
        int N = fileNames.size();
        TFile[] files = new TFile[N];
        // keys: <TFile.name>+<term name>
        HashMap<String, Integer> termsOccurency = new HashMap<>();

    }

    public static Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    public int tf(TFile file, String term , HashMap<String, Integer> termsOccurency) {
        // tf = (N di volte in cui il termine è nel file)/ (N di termini nel file)
        String key = file.getName()+term;
        Integer currentVal = termsOccurency.get(key);
        if (currentVal == null) {
            int val =0;
            for (String t : file.getTerms()) 
                if (t.equals(term)) val++;
                
            
        }
        

        return 0;
    }




    public int idf(TFile currentFile, String term) {
        // idf = (N di docs)/ (N di docs che contengono term)

        return 0;
    }

    public int tfidf(int tf, int idf) {
        return tf * idf;
    }

    public TFile createFile(String fileName) {
        fileName= DIRECTORY_NAME+fileName;
        ArrayList<String> terms = new ArrayList<>();
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] lineTerms = data.split(" ");
                terms.addAll(Arrays.asList(lineTerms));

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String[] termsArr = (String[]) terms.toArray();
        TFile newFile = new TFile(termsArr.length, fileName, termsArr);
        return newFile;
    }
}
