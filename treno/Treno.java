

public class Treno {
    private Locomotiva head = new Locomotiva();
    private Locomotiva tail = new Locomotiva();
    private int totInside = 0;
    private int ID=0;

    public int getTotInside() {
        return this.totInside;
    }
    public int getID() {
        return this.totInside;
    }

    public void setTotInside(int totInside) {
        this.totInside = totInside;
    }

    Treno(int ID) {
        head.setLink(tail, true);
        tail.setLink(head, false);
        this.ID= ID;
    }

    public void addVagone(Vagone vagone) {

        vagone.setNext(tail);
        vagone.setPrev(tail.getPrev());

        tail.getPrev().setNext(vagone);
        tail.setPrev(vagone);

    }

    public Vagone removeVagone(Vagone vagone) {
        if (vagone == head) {
            head.setNext(head.getNext());
        } else if (vagone == tail) {
            tail.setPrev(tail.getPrev());
            ;
            tail.setNext(null);

        }

        Vagone current = head;

        while (!(current.getNext() == vagone)) {
            current = current.getNext();
        }
        Vagone out = current;
        current.setNext(current.getNext().getNext());
        current.getNext().getNext().setPrev(current);
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

    public void printVagone(int index) {
        Vagone current = head;

        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        // print stuff
    }

}
