
public class Treno {
    private Locomotiva head = new Locomotiva();
    private Locomotiva tail = new Locomotiva();
    private int totInside = 0;

    private int ID = 0;

    public int getTotInside() {
        return this.totInside;
    }

    public int getID() {
        return this.ID;
    }

    public void setTotInside(int totInside) {
        this.totInside = totInside;
    }

    Treno(int ID) {
        head.setLink(tail, true);
        tail.setLink(head, false);

        this.ID = ID;
    }

    public void addVagone(Vagone vagone) {

        vagone.setNext(tail);
        vagone.setPrev(tail.getPrev());

        tail.getPrev().setNext(vagone);
        tail.setPrev(vagone);
        this.totInside += vagone.getPasseggeri();

    }

    public Vagone removeVagone(Vagone vagone) {

        if (vagone.equals(head)) {
            head.setNext(head.getNext());
        } else if (vagone.equals(tail)) {
            System.out.println("WTF");
            tail.setPrev(tail.getPrev());

            tail.setNext(null);

        }

        Vagone current = head;

        while (!(current.getNext().equals(vagone))) {
            current = current.getNext();
        }
        System.out.println(current.getPasseggeri());
        Vagone out = current;
        current.setNext(current.getNext().getNext());
        current.getNext().setPrev(current);

        this.totInside -= vagone.getPasseggeri();

        return out;
    }

    /*
     * public void moveVagoneInTrain(Vagone vagoneToMove, int destination) {
     * Vagone current = head;
     * 
     * for (int i = 0; i < destination; i++) {
     * current = current.getNext();
     * }
     * 
     * 
     * removeVagone(vagoneToMove);
     * vagoneToMove.setNext(current);
     * vagoneToMove.setPrev(current.getPrev());
     * 
     * current.getPrev().setNext(vagoneToMove);
     * current.setPrev(vagoneToMove);
     * 
     * }
     */

    public void printTrain() {
        Vagone current = head;
        while (current != null) {
            System.out.println("Vagone: " + current.getClass() + ", Passeggeri: " + current.getPasseggeri());
            current = current.getNext();
        }
        System.out.println("---------------------");

        // print stuff
    }

}
