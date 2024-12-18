import java.util.LinkedList;
import java.util.Queue;

public class BTree {
    private BNode head;

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

   
        if (n == null) return;
        
        inorder(n.getLeft());
        inorder(n.getRight());
        System.out.println(n.getID());
    }

    public void inorder(BNode n) {

        if (n == null) return;

        inorder(n.getLeft());
        System.out.println(n.getID());
        inorder(n.getRight());
    }

    public void preorder(BNode n) {

       
        if (n == null) return;
        
        System.out.println(n.getID());
        inorder(n.getLeft());
        inorder(n.getRight());
        
    }

    public void breadthTraversal(){
        Queue<BNode> q = new LinkedList<>();
        q.add(head);
        while (!q.isEmpty()) {
            BNode current = q.poll();

            if (current.getLeft() != null) {
                q.add(current.getLeft());
            }
            if (current.getRight() != null) {
                q.add(current.getRight());
            }

            System.out.println(current.getID());

            
        }
    }

    public void addNode(BNode newNode){
        Queue<BNode> q = new LinkedList<>();
        q.add(head);
        while (!q.isEmpty()) {
            BNode current = q.poll();

            if (current.getLeft() == null) {
                current.setLeft(newNode);
                break;
            } else if (current.getRight() == null) {
                current.setRight(newNode);
                break;
            }

            q.add(current.getLeft());
            q.add(current.getRight());

            
        }


    }



}
