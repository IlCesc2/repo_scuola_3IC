package zucinternational;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    /** 
     * All the logic of the program
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Country> data = new ArrayList<Country>();
        try(BufferedReader reader = new BufferedReader(new FileReader("zucinternational\\country_full.csv"))) {
            
            String line;
            reader.readLine(); // skip 1st line

            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(",");
                Country country = new Country(splitLine[0], splitLine[1], splitLine[2], splitLine[3], splitLine[4],
                        splitLine[5], splitLine[6], splitLine[7], splitLine[8], splitLine[9], splitLine[10]);
                data.add(country);
            }
        } catch (Exception e) {
            System.out.println("Si è verificato un errore: " + e.getMessage());
        }
        
        System.out.print("Inserire codice: ");
        String code = sc.nextLine();
        for (Country country : data) {
            if (country.hasCode(code)) {
                System.out.println(country.toString());
                break;
            }
        }

    }

}
