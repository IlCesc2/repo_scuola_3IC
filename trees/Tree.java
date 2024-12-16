
public class Tree {
    private Node head;
    private int K;

    public Tree(Node head, int K) {
        this.head = head;
        this.K = K;
    }
    public void initNodes(Node cur){
        for (int i = 0; i < cur.getChildren().length; i++) {
            if(cur.getChildren()[i] == null) cur.setNChildren(i);
        }
        for (Node iterable_element : iterable) {
            
        }
    }

    public Node getHead() {
        return this.head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public void postorder(Node n) {

        if (n.getChildren() == null) {
            System.out.print(n.getID());
            return;
        }
        for (Node child : n.getChildren()) {
            postorder(child);
        }
        System.out.print(n.getID());

    }

    public void inorder(Node n) {

        Node[] children = n.getChildren();
        if (children == null) {
            System.out.print(n.getID());

            return;
        }
        for (int i = 0; i < children.length; i++) {
            inorder(children[i]);
            if (i == 0) // so that it prints the root only once
                System.out.print(n.getID());

        }

    }

    public void preorder(Node n) {

        System.out.print(n.getID());
        Node[] children = n.getChildren();
        if (children == null)
            return;
        for (Node child : children) {
            preorder(child);
        }
    }

    public void addChild(Node newNode){
        head.addChild(newNode, head, K);
    }

}
