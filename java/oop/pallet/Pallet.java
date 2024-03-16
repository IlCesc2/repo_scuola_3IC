public class Pallet implements Capacita {
    private double altezza;
    private double larghezza;
    private double profondita;

    public Pallet(double altezza, double larghezza, double profondita) {
        this.altezza = altezza;
        this.larghezza = larghezza;
        this.profondita = profondita;
    }

    @Override
    public double getVolume() {
        return altezza*larghezza*profondita;
    }
    public String toString() {
        return altezza+ ", "+ larghezza+ ", "+profondita;
    }

    public double getAltezza() {
      return this.altezza;
    }
    public void setAltezza(double value) {
      this.altezza = value;
    }

    public double getLarghezza() {
      return this.larghezza;
    }
    public void setLarghezza(double value) {
      this.larghezza = value;
    }

    public double getProfondita() {
      return this.profondita;
    }
    public void setProfondita(double value) {
      this.profondita = value;
    }
}
