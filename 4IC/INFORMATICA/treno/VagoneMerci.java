import java.util.Random;


public class VagoneMerci extends Vagone {
    private Merce[] merci;
    VagoneMerci(){
        
        merci= new Merce[MAX_PASSEGGERI];
        populate();
    }
    @Override
    public int populate() {
        Random rand = new Random();
        int randomNum = rand.nextInt((merci.length) + 1);
        for (int i = 0; i < randomNum; i++) {
            merci[i] = new Merce();
        }
        this.setPasseggeri(randomNum);
        
        return randomNum;
    }
}
