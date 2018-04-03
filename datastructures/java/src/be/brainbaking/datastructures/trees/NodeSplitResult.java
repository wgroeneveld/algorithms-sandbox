package be.brainbaking.datastructures.trees;

public class NodeSplitResult {

    private final Node newNode;
    private final String splitKey;

    public Node getNewNode() {
        return newNode;
    }

    public String getSplitKey() {
        return splitKey;
    }

    public NodeSplitResult(Node newNode, String splitKey) {
        this.newNode = newNode;
        this.splitKey = splitKey;
    }
}
