public class LinkedList {
    private Node head;
    private int size = 0;

    public Node giveHead() {
        return this.head;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void add(String patientName, int priority) {
        Node newPatient = new Node(patientName, priority);
        if (head == null) {
            head = newPatient;
            return;
        }
        Node buf = head;
        while (buf.getNext() != null) {
            buf = buf.getNext();
        }

        buf.setNext(newPatient);
        size++;

    }

    public Node getNext() {
        // gets next in priority
        if (head == null)
            return head;

        Node buf = head.getNext();
        Node sel = head;

        while (buf != null) {
            if (sel.getPriority() < buf.getPriority())
                sel = buf;
            buf = buf.getNext();
        }
        // System.out.println(buf.getPatientName() + buf.getPriority());
        return sel;
    }

    public Node removeNext() {
        // gets next in priority
        if (head == null)
            return null;

        Node buf = head;
        Node sel = head.getNext();
        Node prev = head;
        Node selPrev = prev;

        while (buf != null) {

            if (buf.getPriority() < sel.getPriority()) {
                sel = buf;
                selPrev = prev;
                selPrev.printNode();
                break;
            } 
            prev = buf;
            buf = buf.getNext();
        }

        if (sel.getPatientName().equals(head.getPatientName())) {
            head = head.getNext();
        } else if (sel.getNext() == null) {            
            prev.setNext(null);
        } else { 
            selPrev.setNext(sel.getNext());
        }
        size--;
        return sel;

    }

    public void printAll() {
        System.out.println("------------------");
        if (head == null)
            return;

        Node buf = head;

        while (buf != null) {
            buf.printNode();
            buf = buf.getNext();
        
        }
    }

}
