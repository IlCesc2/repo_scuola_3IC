import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Relation orders = new Relation("5IC/es/ordini.csv");
        Relation ppl = new Relation("5IC/es/persone.csv");
        Relation products = new Relation("5IC/es/prodotti.csv");

        /*
         * 
         * 
         * //Visualizzare il totale per tutti gli ordini
         * 
         * //Visualizzare il totale per tutti gli ordini
         * int totOrders = 0;
         * HashMap<String, Integer> totalPerOrder = new HashMap<>();
         * 
         * for (Row o: orders.rows) {
         * String id_prodotto = o.values.get(2);
         * Integer qt = Integer.parseInt(o.values.get(2));
         * 
         * Relation product = selection(products, "id_prodotto", id_prodotto);
         * Integer prezzoProdotto= Integer.parseInt(product.rows.get(0).values.get(2));
         * 
         * totOrders+=prezzoProdotto*qt;
         * // total per order
         * String id_order = o.values.get(2);
         * totalPerOrder.put(id_order, prezzoProdotto*qt);
         * 
         * }
         * 
         * // getting the most expensive item
         * int maxIndex =0;
         * for (int i = 1; i < products.rows.size(); i++) {
         * Integer maxPrice =
         * Integer.parseInt(products.rows.get(maxIndex).values.get(3));
         * Integer currentPrice = Integer.parseInt(products.rows.get(i).values.get(3));
         * 
         * if (currentPrice>maxPrice) maxIndex=i;
         * }
         * 
         * String maxProductId = products.rows.get(maxIndex).values.get(0);
         * 
         * ArrayList<String> id_utente = new ArrayList<>();
         * 
         * for (int i = 0; i < orders.rows.size(); i++) {
         * String curProductId = orders.rows.get(i).values.get(2);
         * String curUserId = orders.rows.get(i).values.get(2);
         * if(curProductId.equals(maxProductId)){
         * id_utente.add(curUserId);
         * }
         * }
         * 
         * ArrayList<Row> out = new ArrayList<>();
         * for (String id : id_utente) {
         * Relation r = selection(ppl, "id", id);
         * out.addAll(r.rows);
         * }
         * 
         * // OUT
         * System.out.println("Total prices: "+totOrders);
         * System.out.println("Total per order:");
         * for (String key : totalPerOrder.keySet()) {
         * System.out.println(key + ": "+ totalPerOrder.get(key));
         * }
         * 
         * System.out.println("Users that bought the most expensive product: ");
         * for (Row row : out) {
         * row.printRow();
         * }
         */

        Relation ordini = new Relation("5IC/es/ordini.csv");
        Relation prodotti = new Relation("5IC/es/prodotti.csv");
        Relation persone = new Relation("5IC/es/persone.csv");

        System.out.println("ORDINI -------------------");
        ordini.printTable();
        System.out.println("PRODOTTI -------------------");
        prodotti.printTable();
        System.out.println("PERSONE -------------------");
        persone.printTable();

        String[] filter = { "id_prodotto" };

        Relation a = join(ordini, prodotti, filter);

        HashMap<String, Integer> orderTotal = new HashMap<>();
        int idProd = a.header.indexOf("id_prodotto");
        int idQt = a.header.indexOf("qty");
        int idPrezzo = a.header.indexOf("prezzo_unitario");
        int tot = 0;
        for (Row r : a.rows) {
            Integer qt = Integer.parseInt(r.values.get(idQt));
            Integer price = Integer.parseInt(r.values.get(idPrezzo));
            int t = qt * price;
            orderTotal.put(r.values.get(idProd), t);
            tot += t;
        } // TODO: CHECK THIS SOMETHING ISN'T WORKING, ITERATING TWICE ON ELEMENTS 

        // getting the most expensive item
        int maxIndex = 0;
        for (int i = 1; i < products.rows.size(); i++) {
            Integer maxPrice = Integer.parseInt(products.rows.get(maxIndex).values.get(2));
            Integer currentPrice = Integer.parseInt(products.rows.get(i).values.get(2));

            if (currentPrice > maxPrice)
                maxIndex = i;
        }

        String maxProductId = products.rows.get(maxIndex).values.get(0);

        ArrayList<String> id_utente = new ArrayList<>();

        for (int i = 0; i < orders.rows.size(); i++) {
            String curProductId = orders.rows.get(i).values.get(2);
            String curUserId = orders.rows.get(i).values.get(2);
            if (curProductId.equals(maxProductId)) {
                id_utente.add(curUserId);
            }
        }

        ArrayList<Row> out = new ArrayList<>();
        for (String id : id_utente) {
            Relation r = selection(ppl, "id", id);
            out.addAll(r.rows);
        }

        // OUT
        System.out.println("Total prices: " + tot);
        System.out.println("Total per order:");
        for (String key : orderTotal.keySet()) {
            System.out.println(key + ": " + orderTotal.get(key));
        }
        System.out.println("Users who bought the most expensive item:");
        for (Row row : out) {
            row.printRow();
        }

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

    public static Relation projection(Relation input, ArrayList<String> keys) {
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
        if (!checkEquality(one.header, two.header)) {
            System.out.println("The headers need to be equal, rename them.");

            return null;
        }
        Relation out = new Relation(one.header, one.rows);
        out.rows.addAll(two.rows);
        removeRowDuplicates(out);
        return out;
    }

    public static Relation difference(Relation one, Relation two) {
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

    public static Relation prodCartesiano(Relation one, Relation two) {
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

    // JOIN TROUGH SELECTION
    public static Relation joinSel(Relation one, Relation two, String key, String value) {
        Relation prod = prodCartesiano(one, two);
        return selection(prod, key, value);
    }

    // JOIN TROUGH PROJECTION
    /*
     * 
     * public static Relation joinProj(Relation one, Relation two, String[]
     * junctionField) {
     * for (String j : junctionField) {
     * if(!one.header.contains(j) || !two.header.contains(j)){
     * System.out.println("The junction values are not in both of the relations");
     * return null;
     * }
     * }
     * Relation out = new Relation(one.header, one.rows);
     * for (int i = 0; i < junctionField.length; i++) { // JUN FIELDS
     * int oneIndex = one.header.indexOf(junctionField[i]);
     * int twoIndex = two.header.indexOf(junctionField[i]);
     * ArrayList<Row> newRows = new ArrayList<>();
     * for (int j = 0; j < one.rows.size(); j++) { // A
     * Row row1 = one.rows.get(j);
     * for (int j2 = 0; j2 < two.rows.size(); j2++) { // B
     * Row row2 = two.rows.get(j);
     * 
     * if(row1.values.get(oneIndex).equals(row2.values.get(twoIndex))){
     * ArrayList<String> newVals = new ArrayList<>();
     * newVals.addAll(row1.values);
     * newVals.addAll(row2.values);
     * newVals.remove(oneIndex+twoIndex);
     * 
     * Row nRow = new Row(newVals);
     * }
     * }
     * }
     * }
     * }
     */

    public static Relation join(Relation one, Relation two, String[] junctionField) {
        Relation out = prodCartesiano(one, two);
        return search(out, 0, junctionField);
    }

    public static Relation search(Relation one, int i, String[] junctionField) {

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
            for (int j = i + 1; j < input.rows.size(); j++) {
                if (checkEquality(input.rows.get(i).values, input.rows.get(j).values)) {
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