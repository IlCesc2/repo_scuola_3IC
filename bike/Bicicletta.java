package bike;
import java.util.UUID;

enum StatoBicicletta {
    DISPONIBILE,
    PRENOTATA;
}

public class Bicicletta {
    //marca, modello, taglia del telaio, il numero identificativo assegnato e lo stato (disponibile o noleggiata) oltre a tutti i metodi opportuni.
    private String marca;
    private String modello;
    private double tagliaTelaio;
    private int id;
    private StatoBicicletta stato;

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return this.modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public double getTagliaTelaio() {
        return this.tagliaTelaio;
    }

    public void setTagliaTelaio(double tagliaTelaio) {
        this.tagliaTelaio = tagliaTelaio;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StatoBicicletta getStato() {
        return this.stato;
    }

    public void setStato(StatoBicicletta stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "{" +
            " marca='" + getMarca() + "'" +
            ", modello='" + getModello() + "'" +
            ", tagliaTelaio='" + getTagliaTelaio() + "'" +
            ", id='" + getId() + "'" +
            ", stato='" + getStato() + "'" +
            "}";
    }

    public Bicicletta(String marca, String modello, int id, double tagliaTelaio, StatoBicicletta stato) {
        this.marca = marca;
        this.modello = modello;
        this.id = id;
        this.stato = stato;
        this.tagliaTelaio= tagliaTelaio;
    }


    
}
