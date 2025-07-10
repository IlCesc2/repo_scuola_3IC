import java.util.ArrayList;

public class BNode {
    private int value;
    private BNode left;
    private BNode right;

    private ArrayList<Character> chars = new ArrayList<>();

    public ArrayList<Character> getChars() {
        return chars;
    }

    public void setChars(ArrayList<Character> chars) {
        this.chars = chars;
    }

    public void setID(int value) {
        this.value = value;
    }

    public BNode(int value, BNode left, BNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int getID() {
        return this.value;
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
