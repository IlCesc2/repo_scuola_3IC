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

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (header.isEmpty()) {
                    header.add(values[0]);
                    header.add(values[1]);
                    header.add(values[2]);
                } else {

                    Row newRow = new Row();
                    newRow.values.add(values[0]);
                    newRow.values.add(values[1]);
                    newRow.values.add(values[2]);
                    rows.add(newRow);
                }
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
