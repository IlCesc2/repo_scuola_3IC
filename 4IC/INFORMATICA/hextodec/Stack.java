public class Stack {
    private Node head = null;
    private int len = 0;

    public void setHead(Node head) {
        this.head = head;
    }

    public int getLen() {
        return this.len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public Node getHead() {
        return this.head;
    }

    // pop
    public void pop() {
        if (head == null) return;
        head = head.getNext();
        len--;
    }

    // peek
    public Character peek() {
        return head == null ? null : head.getValue();
    }

    // push
    public void push(Character value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        }
        newNode.setNext(head);

        head = newNode;
        len++;
    }

}

class Node {
    private Character value;

    public Node(Character value) {
        this.value = value;
    }

    private Node next = null;

    public Character getValue() {
        return this.value;
    }

    public void setValue(Character value) {
        this.value = value;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
