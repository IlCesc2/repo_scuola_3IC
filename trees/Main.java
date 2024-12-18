public class Main {
    public static void main(String[] args) {
        bTreeTesting();

    }

    public static void kTreeTesting() {
        int K = 2;
        int lastId = 0;
        KNode node4 = new KNode(4, null);
        KNode node5 = new KNode(5, null);
        KNode[] c1 = { node4, node5 };
        KNode node2 = new KNode(2, c1);

        KNode node3 = new KNode(3, null);
        KNode[] c2 = { node2, node3 };
        KNode node1 = new KNode(1, c2);

        KTree tree = new KTree(node1, K);
        KNode node6 = new KNode(6, null);
        System.out.print("In Order: ");
        tree.inorder(tree.getHead());
        System.out.println();
        /*
         * System.out.print("Post Order: ");
         * tree.postorder(tree.getHead());
         * System.out.println();
         * 
         * System.out.print("Pre Order: ");
         * tree.preorder(tree.getHead());
         * System.out.println();
         */
        tree.addChild(node6);
        System.out.print("In Order: ");
        tree.inorder(tree.getHead());
        System.out.println();
    }

    public static void bTreeTesting() {
        BNode node4 = new BNode(4, null, null);
        BNode node5 = new BNode(5, null, null);
        BNode node2 = new BNode(2, null, null);
        BNode node3 = new BNode(3, null, null);
        BNode node1 = new BNode(1, null, null);

        BTree tree = new BTree(node1);
        BNode node6 = new BNode(6, null, null);
        System.out.print("In Order: ");
        tree.inorder(tree.getHead());
        System.out.println();
        /*
         * System.out.print("Post Order: ");
         * tree.postorder(tree.getHead());
         * System.out.println();
         * 
         * System.out.print("Pre Order: ");
         * tree.preorder(tree.getHead());
         * System.out.println();
         */
        tree.addNode(node1);
        tree.addNode(node2);
        tree.addNode(node3);
        tree.addNode(node4);
        tree.addNode(node5);
        tree.addNode(node6);
        System.out.print("In Order: ");
        tree.inorder(tree.getHead());
        System.out.println();
    }
}
