package be.brainbaking.datastructures.trees;

public class BTreeSearchResult {

    private final Node parent;
    private final Node node;
    private final int index;

    public BTreeSearchResult() {
        this(null, -1, null);
    }

    public boolean isFound() {
        return node != null;
    }

    public Node getParent() {
        return parent;
    }

    public BTreeSearchResult(Node node, int index, Node parent) {
        this.node = node;
        this.parent = parent;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Node getNode() {
        return node;
    }

}
