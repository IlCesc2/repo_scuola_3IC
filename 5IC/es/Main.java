import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Relation persone1 = new Relation("5IC/es/persone.csv");
        Relation persone2 = new Relation("5IC/es/persone2.csv");

        System.out.println("TABLE 1 -------------------");
        persone1.printTable();
        System.out.println("TABLE 2 -------------------");
        persone2.printTable();
        // selection(persone1, "nome", "Giovanni").printTable();
        ArrayList<String> filter = new ArrayList<>();
        filter.add("id");
        filter.add("nome");
        filter.add("cognome");
        // projection2(persone1, filter).printTable();

        System.out.println("UNION -------------------");
        union(persone1, persone2).printTable();
        System.out.println("DIFFERENCE -------------------");
        difference(persone1, persone2).printTable();
    }

    public static Relation selection(Relation input, String key, String value) {
        int index = input.header.indexOf(key);

        ArrayList<Row> selectedRows = new ArrayList<>();
        for (Row row : input.rows) {
            if (row.values.get(index).equals(value)) {
                selectedRows.add(row);
            }
        }
        Relation out = new Relation(input.header, selectedRows);

        return out;
    }

    public static Relation projection2(Relation input, ArrayList<String> keys) {
        ArrayList<Row> selectedRows = new ArrayList<>();
        Relation out = new Relation(keys, selectedRows);

        for (Row row : input.rows) {
            ArrayList<String> l = new ArrayList<>();
            for (int i = 0; i < keys.size(); i++) {
                int index = input.header.indexOf(keys.get(i));
                l.add(row.values.get(index));
            }
            Row r = new Row();
            r.values = l;
            selectedRows.add(r);
        }

        removeColumnDuplicates(out);
        return out;
    }

    public static Relation union(Relation one, Relation two) {
        if (!checkEquality(one.header, two.header))
            return null;
        Relation out = new Relation(one.header, one.rows);
        out.rows.addAll(two.rows);
        removeRowDuplicates(out);
        return out;
    }

    public static Relation difference(Relation one, Relation two) {
        if (!checkEquality(one.header, two.header))
            return null;
        Relation out = new Relation(one.header, one.rows);
        for (int i = 0; i < one.rows.size(); i++) {
            for (int j = 0; j < two.rows.size(); j++) {
                //System.out.println(Arrays.toString(one.rows.get(i).values.toArray())+", "+Arrays.toString(two.rows.get(j).values.toArray()));
                if (checkEquality(one.rows.get(i).values, two.rows.get(j).values)) {
                    out.rows.remove(i);
                }
            }
        }

        // out.rows.addAll(two.rows);
        //removeRowDuplicates(out);
        return out;
    }

    // HELPERS

    public static void removeColumnDuplicates(Relation input) {
        
        for (int i = 0; i < input.header.size(); i++) {
            ArrayList<String> column = new ArrayList<>();

            for (int j = 0; j < input.rows.size(); j++) {
                Row r = input.rows.get(j);
                String currentValue = r.values.get(i);
                if (column.contains(currentValue)) {
                    r.values.set(i, "");
                } else {
                    column.add(currentValue);
                }
                
            }

        }
    }

    public static void removeRowDuplicates(Relation input) { // idk

        for (int i = 0; i < input.rows.size(); i++) {
            for (int j = i+1; j < input.rows.size(); j++) {
                if(checkEquality(input.rows.get(i).values, input.rows.get(j).values)){
                    input.rows.remove(input.rows.get(j));
                }
            }
        }
    }

    public static boolean checkEquality(ArrayList<String> a1, ArrayList<String> a2) {
        return Arrays.equals(a1.toArray(), a2.toArray());
    }

    /*
     * public static Relation projection(Relation input, ArrayList<String> keys) {
     * ArrayList<Row> selectedRows = new ArrayList<>();
     * Relation out = new Relation(keys, selectedRows);
     * 
     * for (int i = 0; i < keys.size(); i++) { // iterate trough all of the keys in
     * the filter
     * int index = input.header.indexOf(keys.get(i)); // get the right index for the
     * key
     * ArrayList<String> l = new ArrayList<>();
     * for (Row row : input.rows) {
     * l.add(row.values.get(index)); // get all of the values of the column for each
     * row
     * }
     * // System.out.println(Arrays.toString(nonDupedValues.toArray()));
     * 
     * for (int j = 0; j < l.size(); j++) { // iterate trough all of the values of
     * the current selected column
     * String currentValue = l.get(j);
     * if (selectedRows.size() == j) { // IF THERE ARE NO more rows we need to
     * create a new one
     * Row newRow = new Row();
     * for (int k = 0; k < input.header.size(); k++) { // add all blanks except for
     * the right value at the
     * // rioght index
     * newRow.values.add(index == k ? currentValue : "");
     * }
     * selectedRows.add(newRow);
     * } else { // if there are other rows, i add the value
     * selectedRows.get(j).values.set(index, currentValue);
     * }
     * }
     * 
     * }
     * 
     * //removeDuplicates(out);
     * return out;
     * }
     */

}