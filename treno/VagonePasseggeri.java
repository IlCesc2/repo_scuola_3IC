import java.util.Random;

public class VagonePasseggeri extends Vagone {
    private Passeggero[] passeggeri;
    VagonePasseggeri(){
        
        passeggeri= new Passeggero[MAX_PASSEGGERI];
    }
    @Override
    public int populate() {
        Random rand = new Random();
        int randomNum = rand.nextInt((passeggeri.length) + 1);
        for (int i = 0; i < randomNum; i++) {
            passeggeri[i] = new Passeggero();
        }
        this.setPasseggeri(randomNum);
        return randomNum;
    }
}
