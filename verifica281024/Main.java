import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        List pQueue = new List();
        UUID[] registeredIDs = new UUID[10]; // testing lenght, to hold all of the "registered IDs"

        // populates the IDs array
        generateIDs(registeredIDs);
        for (UUID uuid : registeredIDs) {
            System.out.println(uuid.toString());
        }

        Scanner scan = new Scanner(System.in);
        int option = -1;
        int mainCounter = 0;

        System.out.println("Inserire opzione");
        System.out.println(
                "1) operazioni postali\r\n" +
                        "2) operazioni in denaro;\r\n" +
                        "3) consulenza risparmio");

        while ((option = scan.nextInt()) != 0) {

            Utente newUser = new Utente(mainCounter, option);
            System.out.println("Inserire ID se possibile (inserire vuoto altrimenti): ");
            String sId = scan.next();
            try {
                UUID id = UUID.fromString(sId);
                // checks if IDs is inside of the array and if it's already registered
                if (checkValidID(id, registeredIDs) && !pQueue.isUserAlreadyRegistered(id)) {
                    System.out.println("ID valid, registrazione con priorità in coda.");

                    newUser.setId(id);
                }
            } catch (Exception e) {
                // if the id isn't valid it adds it as normal user without priority
                System.out.println("ID invalido, registrazione normale in coda.");
            }

            pQueue.addNewUser(newUser);
            mainCounter++;

            // pQueue.printALL();
            System.out.println("Inserire opzione: ");
        }

        System.out.println("Inserire opzione");
        System.out.println("0) shutdown\r\n" + "1) Chiamare prossimo utente per operazioni postali\r\n" +
                "2)Chiamare prossimo utente per operazioni in denaro;\r\n" +
                "3)Chiamare prossimo utente per consulenza risparmio");

        while ((option = scan.nextInt()) != 0 && pQueue.queueSize() > 0) {

            Utente userTreated = pQueue.removeUser(option);
            String out = userTreated != null ? "User trattato: " + userTreated.toString() + " in sportello " + option
                    : "Nessun utente in coda";
            System.out.println(out);
            // pQueue.printALL();

            System.out.println("Inserire opzione: ");
        }

    }

    // checks if id is in the array
    public static boolean checkValidID(UUID id, UUID[] registeredIDs) {
        for (UUID i : registeredIDs) {
            if (i.equals(id)) {
                return true;
            }
        }
        return false;
    }

    // populates UUID array
    public static void generateIDs(UUID[] registeredIDs) {
        for (int i = 0; i < registeredIDs.length; i++) {
            registeredIDs[i] = UUID.randomUUID();
        }
    }
}
