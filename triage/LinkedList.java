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

        while (buf.getNext() != null) {
    /*
            System.out.print("Prev:");
            prev.printNode();
            System.out.print("sel:");

            sel.printNode();
            System.out.print("buf:");

            buf.printNode();
            System.out.println("--");
     */

            if (buf.getPriority() > buf.getNext().getPriority()) {
                System.out.print("Prev:");
                prev.printNode();
                System.out.print("sel:");
        
                sel.printNode();
                System.out.print("buf:");
        
                buf.printNode();
                System.out.println("--");
                prev = buf;
                sel = buf.equals(head) ? buf : buf.getNext();
                break;
            }

            buf = buf.getNext();
        }
    

        if (sel.getPatientName().equals(head.getPatientName())) {
            head = head.getNext();
        } else if (sel.getNext() == null) {
            prev.setNext(null);
        } else {

            prev.setNext(sel.getNext());
        }

        // System.out.println(sel.getPatientName() + sel.getPriority());

        size--;
        return sel;

    }
    /*
     * while (buf != null) {
     * if (sel.getPriority() > buf.getPriority()) {
     * prev = sel;
     * sel = buf;
     * }
     * buf = buf.getNext();
     * }
     * 
     * if (sel.equals(head)) {
     * head = head.getNext();
     * } else if (sel.equals(prev)) {
     * sel.setNext(null);
     * } else {
     * prev.setNext(prev.getNext().getNext());
     * }
     */

    public void printAll() {
        if (head == null)
            return;

        Node buf = head;

        while (buf != null) {
            buf.printNode();
            buf = buf.getNext();
        }
    }

}
