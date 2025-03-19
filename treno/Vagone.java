

public class Vagone {
    static int MAX_PASSEGGERI = 100;
    private Vagone next;
    private Vagone prev;
    private int passeggeri =0; // qt


    public int getPasseggeri() {
        return this.passeggeri;
    }

    public void setPasseggeri(int passeggeri) {
        this.passeggeri = passeggeri;
    }

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
    public int populate(){
        return 0;
     }

}
