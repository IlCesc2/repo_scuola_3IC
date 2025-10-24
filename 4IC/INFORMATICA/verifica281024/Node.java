public class Node {
    private Node next=null;
    private Node prev=null;
    private Utente user=null;

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return this.prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Utente getUser() {
        return this.user;
    }

    public void setUser(Utente user) {
        this.user = user;
    }

    public Node(Utente user) {
        this.user = user;
    }

}
