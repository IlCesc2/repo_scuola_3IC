package file;

import java.io.File; 
import java.io.FileNotFoundException; 
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class FileSort {
  /*
   * legge il nome del file da leggere ( parole.txt)
   * - scorre il file e carica su un array le parole
   * - esegue il sorting delle parole usando un algoritmo a piacere
   * - scrive le parole ordinate in un secondo file (sorted_parole.txt)
   */
  public static void main(String[] args) {
    String PAROLE_PATH = "file\\parole.txt";
    String OUT_PATH = "file\\sorted_parole.txt";
    long start2 = System.currentTimeMillis();
      
    try {
      File paroleFile = new File(PAROLE_PATH);
      Scanner paroleFileReader = new Scanner(paroleFile);

      // Path path = Paths.get(PAROLE_PATH);
      int N = 95193;// (int) Files.lines(path).count();

      String[] parole = new String[N];
      int i = 0;
      while (paroleFileReader.hasNextLine()) {
        String data = paroleFileReader.nextLine();

        parole[i] = data;
        i++;
      }
      paroleFileReader.close();
      mergeSort(parole);
      File outFile = new File(OUT_PATH);
      FileWriter outFileWriter = new FileWriter(outFile);
      for (int j = 0; j < parole.length; j++) {
        
        outFileWriter.write(parole[j] + "\n");
      }
      outFileWriter.close();
      long end2 = System.currentTimeMillis();  
      System.out.println("Time in millis: "+ (end2-start2));
    } catch (Exception e) {
      System.out.println("Error");
    }
  }

  public static void mergeSort(String[] arr) {
    if (arr.length == 1)
      return;
    String[] a = Arrays.copyOfRange(arr, 0, (arr.length + 1) / 2);
    String[] b = Arrays.copyOfRange(arr, (arr.length + 1) / 2, arr.length);

    mergeSort(a);
    mergeSort(b);

    String[] o = merge(a, b);
    for (int i = 0; i < o.length; i++)
      arr[i] = o[i];
  }

  public static String[] merge(String[] a, String[] b) {
    String[] out = new String[a.length + b.length];
    int i = 0, ia = 0, ib = 0;
    while (ia < a.length && ib < b.length) {
    
      if (a[ia].compareTo(b[ib]) < 0) {
        out[i] = a[ia];
        ia++;
      } else {
        out[i] = b[ib];
        ib++;
      }
      i++;
    }
    while (ia < a.length) {
      out[i] = a[ia];
      i++;
      ia++;
    }
    while (ib < b.length) {
      out[i] = b[ib];
      i++;
      ib++;
    }
    return out;
  }
  
}
