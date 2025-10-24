import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String ROOT = "INFORMATICA\\es2\\";
        Operations ops = new Operations();


        Relation cities = new Relation(ROOT+"city.csv");
        Relation countries = new Relation(ROOT+"country.csv");
        Relation countryLanguages = new Relation(ROOT+"countrylanguage.csv");


        //trova tutte le nazioni Europee
        Relation euNations = ops.selection(countries, "Continent", "Europe");

        //trova tutte le città della Francia
        Relation frenchCities = ops.selection(cities, "CountryCode", "FRA");

        // trova il nome delle nazioni che hanno una popolazione compresa tra 100 milioni e 200 milioni di abitanti
        int min = 100;
        int max = 200;
        ArrayList<Row> ar = new ArrayList<>();
        for (Row r: countries.rows) {
            String s = r.values.get(6);
            if(s.equals("NULL")) continue;
            Integer population= Integer.parseInt(s) /6;

            if(population>= 100 && population<= 200){
                ar.add(r);
            }
        }
        Relation c2 = new Relation(countries.header, ar);

        //trova, per tutte le nazioni del sud America, il nome della capitale, la popolazione e nome dello stato
        String filterA = {"Capital","Population","Name" }
        Relation c3 = 

        /*
        
        
        
        trova le nazioni asiatiche con numero di abitanti maggiore di quello del Giappone.
        trova per l’Italia, la  popolazione della città col maggior numero di abitanti e la popolazione della città col minor numero di abitanti
        trova tutte le nazioni in cui si parla inglese e non si parla francese
         */
    }
}
