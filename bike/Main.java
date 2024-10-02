package bike;
import java.util.Random;
import java.util.Scanner;

class Main {
/*    Un'attività di noleggio biciclette ha deciso di realizzare un'applicazione per la gestione del proprio servizio. 
In una prima fase di sviluppo è stata commissionata la creazione del software che dovrà rappresentare il proprio parco mezzi. 
A questo scopo viene predisposta la classe Bicicletta che memorizza per ogni bicicletta: marca, modello, taglia del telaio, il numero identificativo assegnato e lo stato (disponibile o noleggiata) oltre a tutti i metodi opportuni.
Viene inoltre prevista una classe derivata EBike. Come noto le e-bike sono dotate di una batteria e di un motore in grado di assistere la pedalata; per questo tipo di cicli viene memorizzata: l'autonomia massima teorica (in km) e di quanti livelli di assistenza alla pedalata disponga (i valori andranno da 1 a un massimo che potrà variare da una all'altra bici). 
Viene quindi modellata una classe RentABike che gestisce le attività di noleggio delle bicilette tramite un ArrayList. Quest'ultima classe, oltre a tutti i metodi necessari a gestire le biciclette destinate al noleggio, disporrà dei seguenti metodi:
mostraDisponibili(): mostra a schermo tutte le biciclette disponibili (non noleggiate) complete delle proprie caratteristiche
noleggiaBike (int id): che permette a partire dal numero identificativo di noleggiare una bicicletta impostando lo stato di conseguenza.
rientroBike (int id): che permette a partire dal numero identificativo di segnare come nuovamente disponibile una bicicletta impostando lo stato di conseguenza.
mostraEBike(): mostra a schermo tra tutte le bici inventariate solamente le e-bike  
Predisporre un programma di test che attraverso un apposito menù consenta di verificare la corretta funzionalità di quanto progettato e permetta di archiviare in modo non volatile i dati raccolti.

 */
    public static void main(String[] args) {
        RentABike rent = new RentABike();
        Random rand = new Random();

        Scanner scan = new Scanner(System.in);
        int option = -1;
        while (option != 0) {
            System.out.println("Inserire opzione");
            option = scan.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Bici disponibili:");
                    rent.mostraDisponibili();
                    break;
                case 2:
                    System.out.print("Inserire Id: ");
                    int bikeId = scan.nextInt();
                    System.out.println(rent.noleggiaBike(bikeId));
                    break;
                case 3:
                    System.out.print("Inserire Id: ");
                    int bikeId2 = scan.nextInt();
                    System.out.println(rent.rientroBike(bikeId2));
                    break;
                case 4:
                System.out.println("EBike:");
                rent.mostraDisponibili();
                    break;

                default: break;
            }
        }
    }
    
}