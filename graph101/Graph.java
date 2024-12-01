import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
        //      set currentVertexVisited = true
        //      to next vertex
        // if all of the edges have been traversed, returns true

        for (int i = 0; i < edges.size(); i++) {
            ArrayList<Integer> vertex = edges.get(i);
            HashMap<Integer,Boolean> visited= new HashMap<>();
            for (Node node : nodes) {
                visited.put(node.getId(), nodes.indexOf(node) == i);
            }
            traverse(edges, visited, vertex);
            for (Integer id : visited.keySet()) {
                if(!visited.get(id) && id!= nodes.get(i).getId()) return false;
            }
        }
        return true;

    }

    public void traverse(ArrayList<ArrayList<Integer>> edges, HashMap<Integer,Boolean> visited, ArrayList<Integer> vertex){
        for (int i = 0; i < vertex.size(); i++) {
            if (vertex.get(i) !=0 && !visited.get(nodes.get(i).getId())) {
                visited.put(nodes.get(i).getId(), true);
                traverse(edges, visited, edges.get(i));
            }
        }
    }
    

}
