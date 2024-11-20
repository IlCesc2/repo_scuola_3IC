import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    private ArrayList<Node> nodes = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> edges= new ArrayList<>();

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

    

}
