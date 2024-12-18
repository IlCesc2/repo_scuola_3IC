public class KNode {
    private int ID;
    private KNode[] children;
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

    public KNode(int ID, KNode[] children) {
        this.ID = ID;
        this.children = children;
    }

    public int getID() {
        return this.ID;
    }

    public KNode[] getChildren() {
        return this.children;
    }

    public void setChildren(KNode[] children) {
        this.children = children;
    }

    public void editChild(int ID, KNode newNode) {
        for (int i = 0; i < children.length; i++) {
            if (children[i].ID == ID) {
                children[i] = newNode;
            }
        }
    }

    public int addChild(KNode newNode, KNode node, int K) {
        if (node.NChildren < K) {
            node.children[node.NChildren] = newNode;            
            return 0;
        }
        for (KNode child : node.children) {
            if (addChild(newNode, child, K) ==0) break;
        }
        return 1;

    }

}