public class BNode {
    private int ID;
    private BNode left;
    private BNode right;
    public void setID(int ID) {
        this.ID = ID;
    }

    public BNode(int ID, BNode left, BNode right) {
        this.ID = ID;
        this.left = left;
        this.right = right;
    }

    public int getID() {
        return this.ID;
    }


    public BNode getLeft() {
        return this.left;
    }

    public void setLeft(BNode left) {
        this.left = left;
    }

    public BNode getRight() {
        return this.right;
    }

    public void setRight(BNode right) {
        this.right = right;
    }
}
