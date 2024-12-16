public class Main {
    public static void main(String[] args) {
        int K = 2;
        int lastId = 0;
        Node node4 = new Node(4, null);
        Node node5 = new Node(5, null);
        Node[] c1 = { node4, node5 };
        Node node2 = new Node(2, c1);

        Node node3 = new Node(3, null);
        Node[] c2 = { node2, node3 };
        Node node1 = new Node(1, c2);

        Tree tree = new Tree(node1, K);
        Node node6 = new Node(6, null);
        System.out.print("In Order: ");
        tree.inorder(tree.getHead());
        System.out.println();
        /*
        System.out.print("Post Order: ");
        tree.postorder(tree.getHead());
        System.out.println();

        System.out.print("Pre Order: ");
        tree.preorder(tree.getHead());
        System.out.println();
         */
        tree.addChild(node6);
        System.out.print("In Order: ");
        tree.inorder(tree.getHead());
        System.out.println();

    }
}
