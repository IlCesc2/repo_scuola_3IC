package bike;
import java.util.ArrayList;


public class RentABike {
    /*
     Viene quindi modellata una classe RentABike che gestisce le attività di noleggio delle bicilette tramite un ArrayList. 
     Quest'ultima classe, oltre a tutti i metodi necessari a gestire le biciclette destinate al noleggio, disporrà dei seguenti metodi:
mostraDisponibili(): mostra a schermo tutte le biciclette disponibili (non noleggiate) complete delle proprie caratteristiche
noleggiaBike (int id): che permette a partire dal numero identificativo di noleggiare una bicicletta impostando lo stato di conseguenza.
rientroBike (int id): che permette a partire dal numero identificativo di segnare come nuovamente disponibile una bicicletta impostando lo stato di conseguenza.
mostraEBike(): mostra a schermo tra tutte le bici inventariate solamente le e-bike  

     */
    ArrayList<Bicicletta> bici = new ArrayList<Bicicletta>();
    
    public void mostraDisponibili() {
        for (Bicicletta bicicletta : bici) {
            if(bicicletta.getStato() == StatoBicicletta.DISPONIBILE){
                System.out.println(bicicletta.toString());
            }
        }
    }

    public String noleggiaBike (int id) {
        if (bici.isEmpty() ) return "Nessuna bici in magazzino";
        for (Bicicletta bicicletta : bici) {
            if (bicicletta.getId()== id) {
                if (bicicletta.getStato() == StatoBicicletta.PRENOTATA) {
                    return "Bici già prenotata";
                }
                bicicletta.setStato(StatoBicicletta.PRENOTATA);
                return "Prenotazione eseguita";
            }
        }
        return "Bicicletta non esistente";
    }
    public String rientroBike (int id) {
        if (bici.isEmpty() ) return "Nessuna bici in magazzino";
        for (Bicicletta bicicletta : bici) {
            if (bicicletta.getId()== id) {
                if (bicicletta.getStato() == StatoBicicletta.DISPONIBILE) {
                    return "Bici già disponibile";
                }
                bicicletta.setStato(StatoBicicletta.DISPONIBILE);
                return "Bici nuovamente disponibile";
            }
        }
        return "Bicicletta non esistente";
    }
    public void mostraEBike() {
        if (bici.isEmpty() ) {
            System.out.println("Nessuna bici in magazzino");
            return;
        }
        for (Bicicletta bicicletta : bici) {
            if (bicicletta instanceof EBike) {
                System.out.println(bicicletta.toString());
            }
        }
    }

}
