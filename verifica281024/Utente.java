import java.util.UUID;

public class Utente {
    private int n;
    private int reason;

    private UUID id = null;

    
    public Utente(int n, int reason) {
        this.n = n;
        this.reason = reason;
        
    }
    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getN() {
        return this.n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getReason() {
        return this.reason;
    }

    public void setReason(int reason) {
        this.reason = reason;
    }

    public boolean hasCard(){
        return id != null;
    }

    /*
     * 1) operazioni postali
     * 2) operazioni in denaro;
     * 3) consulenza risparmio
     */
    public String reasonToString() {
        switch (reason) {
            case 1:
                return "operazioni postali";
            case 2:
                return "operazioni in denaro";
            case 3:
                return "consulenza risparmio";
            default:
                return "Ragione Invalida";
        }
    }

    public String toString() {
        return "Numero coda: " + n +" "+ reasonToString() +" Priorità: "+ hasCard();
    }
}
