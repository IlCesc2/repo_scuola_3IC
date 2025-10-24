import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        String PARENT_DIR ="";// "5IC/es/";
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

        Relation ordini = new Relation(PARENT_DIR + "ordini.csv");
        Relation prodotti = new Relation(PARENT_DIR +"prodotti.csv");
        Relation persone = new Relation(PARENT_DIR +"persone.csv");

        Operations ops = new Operations();

        System.out.println("ORDINI -------------------");
        ordini.printTable();
        System.out.println("PRODOTTI -------------------");
        prodotti.printTable();
        System.out.println("PERSONE -------------------");
        persone.printTable();

        String[] filter = { "id_prodotto" };

        Relation a = ops.join(ordini, prodotti, filter);
        a.printTable();

        HashMap<String, Integer> orderTotal = new HashMap<>();

        int idQt = a.header.indexOf("qty");
        int idPrezzo = a.header.indexOf("prezzo_unitario");
        int idOrdine = a.header.indexOf("id");

        int tot = 0;
        for (Row r : a.rows) {
            Integer qt = Integer.parseInt(r.values.get(idQt));
            Integer price = Integer.parseInt(r.values.get(idPrezzo));
            int t = qt * price;
            orderTotal.put(r.values.get(idOrdine), t);
            tot += t;
        } 

        

        // getting the most expensive item
        int maxIndex = 0;
        for (int i = 1; i < prodotti.rows.size(); i++) {
            Integer maxPrice = Integer.parseInt(prodotti.rows.get(maxIndex).values.get(2));
            Integer currentPrice = Integer.parseInt(prodotti.rows.get(i).values.get(2));

            if (currentPrice > maxPrice)
                maxIndex = i;
        }

        String maxProductId = prodotti.rows.get(maxIndex).values.get(0);

        ArrayList<String> id_utente = new ArrayList<>();

        for (int i = 0; i < ordini.rows.size(); i++) {
            String curProductId = ordini.rows.get(i).values.get(2);
            String curUserId = ordini.rows.get(i).values.get(2);
            if (curProductId.equals(maxProductId)) {
                id_utente.add(curUserId);
            }
        }

        Set<Row> out = new HashSet<>();
        for (String id : id_utente) {
            Relation r = ops.selection(persone, "id", id);
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

    
}