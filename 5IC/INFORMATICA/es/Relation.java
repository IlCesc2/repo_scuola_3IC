import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

class Row {
    ArrayList<String> values = new ArrayList<>();;

    public void printRow() {

        System.out.println(Arrays.toString(values.toArray()));
    }
}

public class Relation {
    ArrayList<String> header = new ArrayList<>();
    ArrayList<Row> rows = new ArrayList<>();

    Relation(String csvfile) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvfile))) {
            String line;
            int i =0; 
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Row newRow = new Row();

                for (String val : values) {
                    if (i==0) {
                        header.add(val);
                    } else {
                        newRow.values.add(val);
                    }
                }
                if (i>0) rows.add(newRow);

                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }

    Relation(ArrayList<String> header, ArrayList<Row> rows) {
        this.header = header;
        this.rows = rows;
    }

    public void printTable(){

        System.out.println(Arrays.toString(header.toArray()));

        for (Row r : rows) {
            r.printRow();
        }
        System.out.println();
    }

}
