import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Graph {
    private ArrayList<Node> nodes = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> edges = new ArrayList<>();

    public Graph(Node[] ns) {
        for (Node node : ns) {
            nodes.add(node);
            edges.add(initArr(ns.length));

        }

    }

    public ArrayList<Integer> initArr(int N) {
        ArrayList<Integer> out = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            out.add(0);
        }
        return out;
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void removeNode(Node node) {
        removeAt(this.nodes.indexOf(node));
    }

    public void removeAt(int index) {
        this.nodes.remove(this.nodes.get(index));
        for (ArrayList<Integer> edge : edges) {
            edge.remove(edge.get(index));
        }
        this.edges.remove(this.edges.get(index));
    }

    public void addEdge(int source, int[][] wDestinations) {

        for (int[] i : wDestinations) {

            edges.get(source).set(i[0], i[1]);
        }
    }

    public void removeEdge(int source, int destination) {
        edges.get(source).set(destination, 0);
    }

    public void show() {
        for (ArrayList<Integer> edge : edges) {
            Object[] e = edge.toArray();
            System.out.println(Arrays.toString(e));
        }
        System.out.println("---------------");

    }

    public boolean isFullyConnected() {
        if (nodes.size() < 1)
            return false;
        for (int i = 0; i < edges.size(); i++) {
            for (int j = 0; j < edges.get(i).size(); j++) {
                // checks symmetrically if there is a direct connection between two nodes
                if (edges.get(i).get(j) == 0 && edges.get(j).get(i) == 0)
                    return false;
            }
        }
        return true;
    }

    public Node maxOrder() {
        if (nodes.size() == 0)
            return null;
        if (nodes.size() == 1)
            return nodes.get(0);

        int[] orders = calcOrders();
        int max = Integer.MIN_VALUE;
        for (int i : orders) {
            if (max < i)
                max = i;
        }
        return nodes.get(max);
    }

    public Node minOrder() {
        if (nodes.size() == 0)
            return null;
        if (nodes.size() == 1)
            return nodes.get(0);

        int[] orders = calcOrders();
        int min = Integer.MAX_VALUE;
        for (int i : orders) {
            if (min > i)
                min = i;
        }
        return nodes.get(min);
    }

    public int[] calcOrders() {
        int[] orders = new int[edges.size()];

        for (int i = 0; i < edges.size(); i++) {
            for (int j = 0; j < edges.get(i).size(); j++) {
                if (edges.get(i).get(j) != 0)
                    orders[i]++;
            }
        }
        return orders;
    }

    /*
     * 
     * 
     * public boolean isConnected(int startingIndex, int direction) {
     * if (nodes.size() <1) return false;
     * //System.out.println("Direction:"+ direction);
     * for (int j = startingIndex; direction == 1 ? j < edges.get(0).size(): j > -1;
     * j+=direction) {
     * //System.out.println(startingIndex+", "+j+", "+ (edges.get(0).size()-1));
     * 
     * if (startingIndex == j && j == edges.get(0).size()-1) {
     * //System.out.println("IN");
     * continue;
     * }
     * if (edges.get(startingIndex).get(j) != 0) {
     * 
     * return isConnected(j, direction);
     * }
     * }
     * //System.out.println("out");
     * boolean cond =direction == 1 ? startingIndex !=
     * edges.get(startingIndex).size()-1 :startingIndex!=0 ;
     * if (cond) return false;
     * return direction == 1 ? isConnected(startingIndex, -1): true;
     * }
     */
    public boolean isConnected() {

        // loop trough all nodes
        // loop trough all edges of node
        // if there is an edge, then:
        // set currentVertexVisited = true
        // to next vertex
        // if all of the edges have been traversed, returns true

        for (int i = 0; i < edges.size(); i++) {
            ArrayList<Integer> vertex = edges.get(i);
            HashMap<Integer, Boolean> visited = new HashMap<>();
            for (Node node : nodes) {
                visited.put(node.getId(), nodes.indexOf(node) == i);
            }
            traverse(edges, visited, vertex);
            for (Integer id : visited.keySet()) {
                if (!visited.get(id) && id != nodes.get(i).getId())
                    return false;
            }
        }
        return true;

    }

    public void traverse(ArrayList<ArrayList<Integer>> edges, HashMap<Integer, Boolean> visited,
            ArrayList<Integer> vertex) {
        for (int i = 0; i < vertex.size(); i++) {
            if (vertex.get(i) != 0 && !visited.get(nodes.get(i).getId())) {
                visited.put(nodes.get(i).getId(), true);
                traverse(edges, visited, edges.get(i));
            }
        }
    }
    /*     
    [0, 1, 0, 0]
    [0, 0, 1, 0]
    [0, 0, 0, 1]
    [1, 0, 0, 0]
     */

    public int[] djkstra(int source) {
        int[] D = new int[nodes.size()];
        int[] P = new int[nodes.size()];
        
        ArrayList<Node> Q = (ArrayList<Node>) nodes.clone();
        for (int i = 0; i < D.length; i++) {
            D[i] = Integer.MAX_VALUE;
        }
        D[source] = 0;
        P[source] = -1;

        while (Q.size() > 0) {
            int min = Integer.MAX_VALUE;
            int u = 0;

            for (int i = 0; i < Q.size(); i++) {
                if (D[nodes.indexOf(Q.get(i))] < min) {
                    u = i;
                    min = D[i];
                }
            }

            Node currentVertex = Q.get(u);
            Q.remove(u);

            ArrayList<Integer> currentEdges = edges.get(nodes.indexOf(currentVertex));
            u=nodes.indexOf(currentVertex);

            for (int v = 0; v < D.length; v++) {
                if (currentEdges.get(v) == 0)
                    continue; // no edge in matrix
           
                int sum = D[u] + currentEdges.get(v);
                if (sum < D[v]) {
                    D[v] = sum;
                    P[v] = u;
                }
            }

        }

        return D;
    }

    public int[] bellman_ford(int source) {
        int[] D = new int[nodes.size()];
        int[] P = new int[nodes.size()];

        for (int i = 0; i < D.length; i++) {
            D[i] = Integer.MAX_VALUE;
        }
        D[source] = 0;

        for (int i = 0; i < edges.size(); i++) {
            ArrayList<Integer> currentVertex = edges.get(i);
            for (int v = 0; v < P.length; v++) {
                if (currentVertex.get(v) == 0)
                    continue; // no edge in matrix
                int sum = D[i] + currentVertex.get(v);
                if (sum < D[v]) {
                    D[v] = sum;
                    P[v] = i;
                }
            }
        }

        loop: for (int i = 0; i < edges.size(); i++) {
            ArrayList<Integer> currentVertex = edges.get(i);
            for (int v = 0; v < edges.get(0).size(); v++) {
                if (currentVertex.get(v) == 0)
                    continue;
                int sum = D[i] + currentVertex.get(v);
                if (sum < D[v]) {
                    System.out.println("C'è un loop negativo");
                    break loop;
                }
            }
        }

        return D;
    }
}
