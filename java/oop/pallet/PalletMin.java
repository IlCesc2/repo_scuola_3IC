/**
 * PalletMin
 */
public class PalletMin extends Pallet {

    boolean contieneBatterieLitio;

    public PalletMin(double altezza, double larghezza, double profondita, boolean contieneBatterieLitio) {
        super(altezza,larghezza,profondita );
        this.contieneBatterieLitio = contieneBatterieLitio;
    }
    @Override
    public String toString() {
        return this.getAltezza()+ ", "+ this.getLarghezza()+ ", "+this.getProfondita()+", " + this.contieneBatterieLitio;
    }
}