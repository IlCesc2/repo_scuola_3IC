import java.io.InvalidObjectException;
import java.util.EmptyStackException;
import java.util.HashMap;

public class file_exception {
    public static void main(String[] args) {
        /*
         CheckFileExtension() dovrebbe restituire un numero intero che rappresenta il numero di punti che uno studente riceve per aver inviato correttamente un file in Java. 
         Se il file inviato da uno studente termina con l'estensione .java, ottiene 1 punto. Se il file inviato da uno studente non termina con .java, ottiene 0 punti. 
         Se il file inviato è nullo o una stringa vuota, dovrebbe essere lanciata un'eccezione e dovresti dare allo studente -1 punti. Che tipo di eccezione dipende da te.

        In Main(), sarà presente una array bidimensionale degli studenti e i nomi dei file inviati per consentirti di testare il tuo lavoro. 
        Se viene rilevata un'eccezione, assicurarsi di stampare il messaggio di errore.
         */
        HashMap<String, String> users = new HashMap<String, String>();
        users.put("John", " ");
        users.put("Maria", "bb.java");
        users.put("Carl", "");
        users.put("Jimmy", "ex.java");
        for (String user : users.keySet()) {
            String val = users.get(user);
            int points;
            try {
                points = checkFileExtension(val);
                System.out.println(user+": "+ points);
            } catch (EmptyStackException e) {
                points = -1;
                System.out.println(user + ": Inserire file valido");
            } catch (InvalidObjectException e ){
                points = 0;
                System.out.println(user + ": Inserire file con estensione .java");
            }
            
        }
    }
    public static int checkFileExtension(String in) throws EmptyStackException, InvalidObjectException {
        if (in.isEmpty() || in == null) throw new EmptyStackException();
        if (!in.endsWith(".java")) throw new InvalidObjectException("String Invalid");
        
        return 1;
    }
}

