public class Main {
    public static void main(String[] args) {
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node[] nodes = {node0,node1,node2,node3};
        Graph graph = new Graph(nodes);
        graph.show();

        int[][] sEdges = {{1,1},{2,1},{3,1}}; //[[dest, weight]]
        graph.addEdge(0, sEdges);
        graph.show();

        graph.removeEdge(0,1);
        graph.show();
    }
}
