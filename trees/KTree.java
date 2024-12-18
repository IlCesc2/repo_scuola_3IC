
public class KTree {
    private KNode head;
    private int K;

    public KTree(KNode head, int K) {
        this.head = head;
        this.K = K;
    }
    public void initNodes(KNode cur){
        for (int i = 0; i < cur.getChildren().length; i++) {
            if(cur.getChildren()[i] == null) cur.setNChildren(i);
        }
    }

    public KNode getHead() {
        return this.head;
    }

    public void setHead(KNode head) {
        this.head = head;
    }

    public void postorder(KNode n) {

        if (n.getChildren() == null) {
            System.out.print(n.getID());
            return;
        }
        for (KNode child : n.getChildren()) {
            postorder(child);
        }
        System.out.print(n.getID());

    }

    public void inorder(KNode n) {

        KNode[] children = n.getChildren();
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

    public void preorder(KNode n) {

        System.out.print(n.getID());
        KNode[] children = n.getChildren();
        if (children == null)
            return;
        for (KNode child : children) {
            preorder(child);
        }
    }

    public void addChild(KNode newNode){
        head.addChild(newNode, head, K);
    }

}
