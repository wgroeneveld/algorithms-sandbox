package be.brainbaking.datastructures.trees;

public class BTreeSearchResult {

    private final Node node;
    private final int index;

    public BTreeSearchResult() {
        node = null;
        index = -1;
    }

    public boolean isFound() {
        return node != null;
    }

    public BTreeSearchResult(Node node, int index) {
        this.node = node;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Node getNode() {
        return node;
    }

}
