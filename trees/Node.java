public class Node {
    private int ID;
    private Node[] children;
    private int NChildren = 0;
    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNChildren() {
        return this.NChildren;
    }

    public void setNChildren(int NChildren) {
        this.NChildren = NChildren;
    }

    public Node(int ID, Node[] children) {
        this.ID = ID;
        this.children = children;
    }

    public int getID() {
        return this.ID;
    }

    public Node[] getChildren() {
        return this.children;
    }

    public void setChildren(Node[] children) {
        this.children = children;
    }

    public void editChild(int ID, Node newNode) {
        for (int i = 0; i < children.length; i++) {
            if (children[i].ID == ID) {
                children[i] = newNode;
            }
        }
    }

    public int addChild(Node newNode, Node node, int K) {
        if (node.NChildren < K) {
            node.children[node.NChildren] = newNode;            
            return 0;
        }
        for (Node child : node.children) {
            if (addChild(newNode, child, K) ==0) break;
        }
        return 1;

    }

}