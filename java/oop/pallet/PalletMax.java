public class PalletMax extends Pallet{
    int peso;

    public PalletMax(double altezza, double larghezza, double profondita, int peso) {
        super(altezza,larghezza,profondita );
        this.peso = peso;
    }
    @Override
    public String toString() {
        return this.getAltezza()+ ", "+ this.getLarghezza()+ ", "+this.getProfondita()+", " + this.peso;
    }
}