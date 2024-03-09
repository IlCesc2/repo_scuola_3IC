package java.oop.studente;

public class VotoVerifica implements Comparable<VotoVerifica> {
    private double votoTeorico;
    private double votoPratico;
    private double votoFinale;

    public VotoVerifica(double votoTeorico, double votoPratico) {
        this.votoTeorico = votoTeorico;
        this.votoPratico = votoPratico;
        this.votoFinale = (votoPratico+votoTeorico)/2;
    }
    @Override
    public int compareTo(VotoVerifica voto) {
        return (int) (voto.getVotoFinale() - getVotoFinale());
    }
    

    public double getVotoTeorico() {
      return this.votoTeorico;
    }
    public void setVotoTeorico(double value) {
      this.votoTeorico = value;
    }

    public double getVotoPratico() {
      return this.votoPratico;
    }
    public void setVotoPratico(double value) {
      this.votoPratico = value;
    }

    public double getVotoFinale() {
      return this.votoFinale;
    }
    public void setVotoFinale(double value) {
      this.votoFinale = value;
    }
}
