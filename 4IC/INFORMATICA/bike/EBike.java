package bike;

import java.util.UUID;

public class EBike extends Bicicletta{
    private int autonomia;
    private int livelliDiAssistenza;// da 1 a boh

    public int getAutonomia() {
        return this.autonomia;
    }

    public void setAutonomia(int autonomia) {
        this.autonomia = autonomia;
    }

    public int getLivelliDiAssistenza() {
        return this.livelliDiAssistenza;
    }

    public void setLivelliDiAssistenza(int livelliDiAssistenza) {
        this.livelliDiAssistenza = livelliDiAssistenza;
    }

    public EBike(String marca, String modello, int id, double tagliaTelaio, StatoBicicletta stato,int autonomia,int livelliDiAssistenza) {
        super(marca, modello, id, tagliaTelaio, stato);
        this.autonomia=autonomia;
        this.livelliDiAssistenza=livelliDiAssistenza;
    }


}
