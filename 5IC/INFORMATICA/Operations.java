import java.util.ArrayList;
import java.util.Arrays;

public class Operations {
    public  Relation selection(Relation input, String key, String value) {
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

    public  Relation projection(Relation input, ArrayList<String> keys) {
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

    public  Relation union(Relation one, Relation two) {
        if (!checkEquality(one.header, two.header)) {
            System.out.println("The headers need to be equal, rename them.");

            return null;
        }
        Relation out = new Relation(one.header, one.rows);
        out.rows.addAll(two.rows);
        removeRowDuplicates(out);
        return out;
    }

    public  Relation difference(Relation one, Relation two) {
        if (!checkEquality(one.header, two.header)) {
            System.out.println("The headers need to be equal, rename them.");

            return null;
        }
        Relation out = new Relation(one.header, one.rows);
        for (int i = 0; i < one.rows.size(); i++) {
            for (int j = 0; j < two.rows.size(); j++) {
                // System.out.println(Arrays.toString(one.rows.get(i).values.toArray())+",
                // "+Arrays.toString(two.rows.get(j).values.toArray()));
                if (checkEquality(one.rows.get(i).values, two.rows.get(j).values)) {
                    out.rows.remove(i);
                }
            }
        }

        // out.rows.addAll(two.rows);
        // removeRowDuplicates(out);
        return out;
    }

    public  Relation prodCartesiano(Relation one, Relation two) {
        /*
         * if (checkEquality(one.header, two.header)) {
         * System.out.println("The headers contain equal attributes, consider renaming"
         * );
         * return null;
         * }
         */

        ArrayList<String> newHeader = new ArrayList<>();
        ArrayList<Row> newRows = new ArrayList<>();

        newHeader.addAll(one.header);
        newHeader.addAll(two.header);
        for (int i = 0; i < one.rows.size(); i++) {
            for (int j = 0; j < two.rows.size(); j++) {
                Row newRow = new Row();
                newRow.values.addAll(one.rows.get(i).values);
                newRow.values.addAll(two.rows.get(j).values);
                newRows.add(newRow);
            }
        }
        return new Relation(newHeader, newRows);
    }

    public  Relation join(Relation one, Relation two, String[] junctionField) {
        Relation out = prodCartesiano(one, two);
        return search(out, 0, junctionField);
    }

    public  Relation search(Relation one, int i, String[] junctionField) {

        if (i == junctionField.length) {
            return one;
        }
        String key = junctionField[i];

        if (!one.header.contains(key)) {
            System.out.println("Chiave non esiste negli header");
            return null;
        }

        ArrayList<String> newHeader = new ArrayList<>();
        newHeader.addAll(one.header);

        int oneI = newHeader.indexOf(key);
        newHeader.remove(key);
        int twoI = newHeader.indexOf(key) + 1; // because we just removed 1 element

        ArrayList<Row> newRows = new ArrayList<>();

        for (int j = 0; j < one.rows.size(); j++) {

            if (one.rows.get(j).values.get(oneI).equals(one.rows.get(j).values.get(twoI))) {
                Row nRow = new Row();
                nRow.values.addAll(one.rows.get(j).values);
                nRow.values.remove(oneI);
                newRows.add(nRow);
            }

        }
        Relation out = new Relation(newHeader, newRows);
        return search(out, i + 1, junctionField);
    }

    // HELPERS

    public  void removeColumnDuplicates(Relation input) {

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
    public  boolean checkEquality(ArrayList<String> a1, ArrayList<String> a2) {
        return Arrays.equals(a1.toArray(), a2.toArray());
    }
    
    public  void removeRowDuplicates(Relation input) {
        ArrayList<Row> out = new ArrayList<>();

        for (int i = 0; i < input.rows.size(); i++) {
            Row currentRow = input.rows.get(i);
            boolean found = false;
            for (int j = 0; j < out.size(); j++) {
                if (checkEquality(currentRow.values, out.get(j).values)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                out.add(currentRow);
            }
        }
        input.rows = out;
    }
}
