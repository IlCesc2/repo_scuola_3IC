public class PalletMid extends Pallet {
    int numeroPezziSovrapponibili;

    public PalletMid(double altezza, double larghezza, double profondita, int numeroPezziSovrapponibili) {
        super(altezza,larghezza,profondita );
        this.numeroPezziSovrapponibili = numeroPezziSovrapponibili;
    }
    @Override
    public String toString() {
        return super.toString() +", "+ this.numeroPezziSovrapponibili;
    }
}
