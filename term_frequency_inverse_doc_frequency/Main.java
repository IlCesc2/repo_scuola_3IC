import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.lang.Math;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    static String DIRECTORY_NAME = "documenti/";
    static int N;

    public static void main(String[] args) {

        Set<String> fileNames = listFilesUsingJavaIO("documenti");

        N = fileNames.size();
        String[] fileNamesArr = new String[N];
        TFile[] files = new TFile[N];
        int i = 0;
        for (String fName : fileNames) {
            fileNamesArr[i] = fName;
            i++;
        }
        for (int j = 0; j < N; j++) {
            files[j] = createFile(fileNamesArr[j]);
        }

        // keys: <TFile.name>+<term name> = occurency on that file
        HashMap<String, Integer> termsOccurency = new HashMap<>();

        HashMap<String, Double> tfidf = tfidf(files, fileNamesArr, termsOccurency);
        HashMap<String, Double> sortByValue= sortByValue(tfidf);
        for (String k : sortByValue.keySet()) {
            System.out.println(k + "," + sortByValue.get(k));
        }
    }
    public static HashMap<String, Double> sortByValue(HashMap<String, Double> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Double> > list =
               new LinkedList<Map.Entry<String, Double> >(hm.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() {
            public int compare(Map.Entry<String, Double> o1, 
                               Map.Entry<String, Double> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
         
        // put data from sorted list to hashmap 
        HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    public static double tf(TFile file, String term, HashMap<String, Integer> termsOccurency) {
        // tf = (N di volte in cui il termine è nel file)/ (N di termini nel file)
        String key = file.getName() + term;
        String generalKey = file.getName();
        Integer currentVal = termsOccurency.get(key);
        if (currentVal == null) {
            int val = 0;
            for (String t : file.getTerms())
                if (t.equals(term))
                    val++;

            termsOccurency.put(key, val);
            Integer currentGeneralValue = termsOccurency.get(generalKey);
            termsOccurency.put(generalKey, (currentGeneralValue != null ? currentGeneralValue : 0) + val);
        }

        return termsOccurency.get(key) / termsOccurency.get(generalKey);
    }

    public static double idf(TFile file, String term, String[] filenames, HashMap<String, Integer> termsOccurency) {
        // idf = log((N di docs)/ (N di docs che contengono term))
        int occurency = 0;

        for (String fName : filenames) {
            String fileName = fName + term;
            Integer currentOccurency = termsOccurency.get(fileName);
            if (currentOccurency != null) occurency++;
        }

        return Math.log(N / occurency);
    }

    public static HashMap<String, Double> tfidf(TFile[] files, String[] filenames,
            HashMap<String, Integer> termsOccurency) {
        HashMap<String, Double> tfidf = new HashMap<>();

        for (TFile tFile : files) {
            for (String term : tFile.getTerms()) {
                if (tfidf.get(term) == null) {
                    double tf = tf(tFile, term, termsOccurency);
                    double idf = idf(tFile, term, filenames, termsOccurency);
                    tfidf.put(term, tf / idf);
                }
            }
        }
        return tfidf;
    }

    public static TFile createFile(String fileName) {
        String filePath = DIRECTORY_NAME + fileName;
        ArrayList<String> terms = new ArrayList<>();
        try {
            File myObj = new File(filePath);
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
        // doing this because casting isn't working
        String[] termsArr = new String[terms.size()];
        for (int i = 0; i < termsArr.length; i++) {
            termsArr[i] = terms.get(i);
        }

        TFile newFile = new TFile(termsArr.length, fileName, termsArr);
        return newFile;
    }

    

}
