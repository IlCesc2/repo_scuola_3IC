public class Magazzino {
    int startMin =0, startMid = 10, startMax=30;
    Pallet[] pallets = new Pallet[60];

    public boolean addPallet (Pallet newPallet){
        int startI= 0;
        int cap =10;
        if (newPallet.getClass() == PalletMid.class) {
            startI= startMid;
            cap= startMax;
        } else if (newPallet.getClass() == PalletMax.class) {
            startI= startMax;
            cap= pallets.length;
        }

        for (int i = startI; i < cap; i++) {
            if (pallets[i] == null) {
                pallets[i] = newPallet;
                return true;
            }
        }
        return false;
    }
    public Pallet removePallet (int pos) {
        if (pallets[pos] == null) return null;
        Pallet out = pallets[pos];
        pallets[pos] = null;
        return out;
    }

    public String toString() {
        String out ="";

        for (Pallet pallet : pallets) {
            if (pallet != null) {
                out += pallet.toString()+"\n"; 
            }
        }
        return out;
    }
}
