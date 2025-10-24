import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BTree {
    private BNode head;
    private int N;

    public BTree(BNode head) {
        this.head = head;
    }

    public BNode getHead() {
        return this.head;
    }

    public void setHead(BNode head) {
        this.head = head;
    }

    public void postorder(BNode n) {

        if (n == null)
            return;

        inorder(n.getLeft());
        inorder(n.getRight());
        System.out.println(n.getID());
    }

    public void inorder(BNode n) {

        if (n == null)
            return;

        inorder(n.getLeft());
        System.out.println(n.getID());
        inorder(n.getRight());
    }

    public void preorder(BNode n) {

        if (n == null)
            return;

        System.out.println(n.getID());
        inorder(n.getLeft());
        inorder(n.getRight());

    }

    public void breadthTraversal() {
        Queue<BNode> q = new LinkedList<>();
        q.add(head);
        while (!q.isEmpty()) {
            BNode current = q.poll();
            String o = current.getID()+"";

            if (current.getLeft() != null) {
                q.add(current.getLeft());
                Object[] arr = current.getLeft().getChars().toArray();
                o = (arr.length == 0 ? current.getLeft().getID() : Arrays.toString(arr))+ " <- " + o;
            }
            if (current.getRight() != null) {
                q.add(current.getRight());
                Object[] arr = current.getRight().getChars().toArray();
                o+= " -> "+ (arr.length == 0 ? current.getRight().getID() : Arrays.toString(arr));

            }
            if(current.getID()!= 0) System.out.println(o);
        }
    }

    public void append(char c) {
        appendRec(c, head);
        N++;
        checkInvariant(head);
    }

    private void appendRec(char c, BNode cur) {
        if (cur.getRight() != null) {
            if (cur.getRight().getChars().size() > 0) {
                cur.getRight().getChars().add(c);
                return;
            } else {
                appendRec(c, cur.getRight());
            }
        } else if (cur.getLeft().getChars().size() > 0) {
            cur.setID(cur.getID() + 1);
            cur.getLeft().getChars().add(c);
            return;
        } else {
            cur.setID(cur.getID() + 1);
            appendRec(c, cur.getLeft());
        }

    }

    public void delete(int index) {
        if (index < 0 || index >= N)
            return;

        deleteRec(head, index);
        N--;
    }

    private void deleteRec(BNode cur, int index) {
        if (index < cur.getID()) {
            cur.setID(cur.getID() - 1);

            if (cur.getLeft() != null && cur.getLeft().getChars().size() > 0) {
                cur.getLeft().getChars().remove(index);
                if (cur.getLeft().getChars().size() == 0) {
                    cur.setLeft(null);
                }
                return;
            } else {
                deleteRec(cur.getLeft(), index);
            }
        } else {
            if (cur.getRight() != null && cur.getRight().getChars().size() > 0) {
                cur.getRight().getChars().remove(index - cur.getID());
                if (cur.getRight().getChars().size() == 0) {
                    cur.setRight(null);
                }
                return;
            } else {
                deleteRec(cur.getRight(), index - cur.getID());
            }
        }
    }

    public void insert(int index, char c) {
        insertRec(head, index, c);
        N++;
        // breadthTraversal();
        // System.out.println("---------BEFORE");
        checkInvariant(head);

        // breadthTraversal();
        // System.out.println("---------AFTER\n");
    }

    private void insertRec(BNode cur, int index, char c) {
        if (index <= cur.getID()) {
            cur.setID(cur.getID() + 1);

            if (cur.getLeft() == null) { // no nodes in the left
                BNode newNode = new BNode(0, null, null);
                newNode.getChars().add(c);
                cur.setLeft(newNode);
            } else if (cur.getLeft().getChars().size() > 0) { // node already exists and is a leaf
                cur.getLeft().getChars().add(index, c);
                return;
            } else { // node already exists and is a node
                insertRec(cur.getLeft(), index, c);
            }
        } else {
            if (cur.getRight() == null) { // no nodes in the right
                BNode newNode = new BNode(0, null, null);
                newNode.getChars().add(c);
                cur.setRight(newNode);
            } else if (cur.getRight().getChars().size() > 0) { // right alr exists and is leaf
                cur.getRight().getChars().add(index - cur.getID(), c);
                return;
            } else { // right is node
                insertRec(cur.getRight(), index - cur.getID(), c);
            }
        }
    }

    public void checkInvariant(BNode cur) {
        if (cur == null) // base case
            return;

        if (cur.getLeft() != null && cur.getLeft().getChars().size() > 3) { // left is not ok
            ArrayList<Character> leftChars = split(cur.getLeft().getChars(), 0, 2);
            ArrayList<Character> rightChars = split(cur.getLeft().getChars(), 2, 4);
            if (cur.getRight() == null) { // brother doesn't exist
                BNode newNode = new BNode(0, null, null);
                newNode.setChars(rightChars);
                cur.getLeft().setChars(leftChars);
                cur.setRight(newNode);
                cur.setID(2);
            } else { // simple split of the chars in the list

                BNode newNodeR = new BNode(0, null, null);
                BNode newNodeL = new BNode(0, null, null);
                BNode newNode = new BNode(2, newNodeL, newNodeR);

                newNodeR.setChars(rightChars);
                newNodeL.setChars(leftChars);

                cur.setLeft(newNode);
            }

        } else if (cur.getRight() != null && cur.getRight().getChars().size() > 3) {
            /*
             * ArrayList<Character> leftChars = split(cur.getRight().getChars(), 0, 2);
             * ArrayList<Character> rightChars = split(cur.getRight().getChars(), 2, 4);
             * cur.setID(4);
             * 
             * BNode newNodeR = new BNode(0, null, null);
             * BNode newNodeL = new BNode(0, null, null);
             * BNode newNode = new BNode(2, newNodeL, newNodeR);
             * 
             * newNodeR.setChars(leftChars);
             * newNodeL.setChars(cur.getLeft().getChars());
             * 
             * cur.getRight().setChars(rightChars);
             * cur.setLeft(newNode);
             */
            ArrayList<Character> leftChars = split(cur.getRight().getChars(), 0, 2);
            ArrayList<Character> rightChars = split(cur.getRight().getChars(), 2, 4);
            cur.setID(4);

            BNode newNodeR = new BNode(0, null, null);
            BNode newNodeL = new BNode(0, null, null);
            BNode newNode = new BNode(2, newNodeL, newNodeR);

            if (cur.getLeft().getChars().size() == 0) { // left is a node
                newNodeR.setChars(rightChars);
                newNodeL.setChars(leftChars);

                cur.setRight(newNode);
            } else { // left is a leaf
                newNodeL.setChars(cur.getLeft().getChars());
                newNodeR.setChars(leftChars);
                cur.setLeft(newNode);
                cur.getRight().setChars(rightChars);
            }
        }
        checkInvariant(cur.getLeft());
        checkInvariant(cur.getRight());

    }

    // utils
    public ArrayList<Character> split(ArrayList<Character> chars, int start, int end) {
        ArrayList<Character> out = new ArrayList<>();
        for (int i = 0; i < chars.size(); i++) {
            if (i >= start && i < end)
                out.add(chars.get(i));
        }
        return out;
    }

}
