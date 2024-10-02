public class Node {
    private String patientName;
    private int priority;
    private Node next;

    public String getPatientName() {
        return this.patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node(String patientName, int priority) {
        this.patientName = patientName;
        this.priority = priority;
        this.next = null;
    }

    public void printNode() {
        String c = patientName + ", ";
        switch (priority) {
            case 0:
                c += "Rosso";
                break;
            case 1:
                c += "Giallo";
                break;
            case 2:
                c += "Azzurro";
                break;
            case 3:
                c += "Verde";
                break;
            case 4:
                c+="Bianco";
                break;

            default:
                break;
        }
        System.out.println(c);

    }

}
