

public class Vagone {
    static int MAX_PASSEGGERI = 100;
    private Vagone next;
    private Vagone prev;


    public Vagone getNext() {
        return this.next;
    }

    public void setNext(Vagone next) {
        this.next = next;
    }

    public Vagone getPrev() {
        return this.prev;
    }

    public void setPrev(Vagone prev) {
        this.prev = prev;
    }

}
