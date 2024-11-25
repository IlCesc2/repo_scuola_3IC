public class Main {
    public static void main(String[] args) {
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        
       
        Node[] nodes = {node0,node1,node2,node3};
        Graph graph = new Graph(nodes);
        graph.show();

        int[][] sEdges = {{1,1}}; //[[dest, weight]]
        int[][] sEdges2 = {{2,1}};
        int[][] sEdges3 = {{3,1}}; //[[dest, weight]]
        int[][] sEdges4 = {{0,1}}; //[[dest, weight]]
        
        graph.addEdge(0, sEdges);
        graph.addEdge(1, sEdges2);
        graph.addEdge(2, sEdges3);
        graph.addEdge(3, sEdges4);
        graph.show();

        System.out.println(graph.isConnected(0,1));
    }
}
