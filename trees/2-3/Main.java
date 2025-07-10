public class Main {
    public static void main(String[] args) {
        BTree tree = new BTree(new BNode(0, null, null));
        tree.insert(0, 't');
        tree.insert(1, 'h');
        tree.insert(2, 'o');
        tree.insert(3, 'm');
        tree.insert(4, 'a');
        tree.insert(5, 's');
        tree.insert(6, ' ');
        tree.insert(7, 'F');
        tree.insert(8, 'a');
        tree.insert(9, 'n');
        
        //agifelhbcd
        
        // tree.append('A');
        // tree.append('B');
        // tree.append('C');

       
        
        tree.breadthTraversal();
    }
}
