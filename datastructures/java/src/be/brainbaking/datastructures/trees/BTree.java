package be.brainbaking.datastructures.trees;

public class BTree {

    private Node root;
    private final int t;

    public BTree(int t) {
        this.root = Node.createRoot();
        this.t = t;
    }

    public Node getRoot() {
        return root;
    }

    public void add(String key) {
        Node node = root;

        if(node.isFull(t)) {
            NodeSplitResult splitResult = node.split(t);
            Node newNode = Node.createFromSplitResult(node, splitResult);

            root = newNode;
            insertNonFull(newNode, key);
        } else {
            insertNonFull(node, key);
        }

    }

    private void insertNonFull(Node node, String key) {
        if(node.isLeaf()) {
            node.addKey(key);
        } else {
            int i = findRightChildIndexToSearchThrough(node, key);
            Node nodeToSeekThrough = node.getChildren().get(i - 1);

            if(nodeToSeekThrough.isFull(t)) {
                NodeSplitResult splitResult = nodeToSeekThrough.split(t);
                node.addChild(i - 1, splitResult.getNewNode());
                node.addKey(splitResult.getSplitKey());

                if(splitResult.getSplitKey().compareTo(key) > 0) {
                    i++;
                }
            }

            insertNonFull(node.getChildren().get(i - 1), key);
        }
    }

    private int findRightChildIndexToSearchThrough(Node node, String key) {
        int i = node.getNumberOfKeys();
        while(i >= 1 && key.compareTo(node.getKeys().get(i - 1)) < 0) {
            i--;
        }
        i++;
        return i;
    }

    public BTreeSearchResult search(String key) {
        return searchInNode(root, key);
    }

    private BTreeSearchResult searchInNode(Node node, String key) {
        int i = 0;
        while(i < node.getNumberOfKeys() && key.compareTo(node.getKeys().get(i)) > 0) {
            i++;
        }
        if(i < node.getNumberOfKeys() && key == node.getKeys().get(i)) {
            return new BTreeSearchResult(node, i);
        }

        if(node.isLeaf()) return new BTreeSearchResult();
        return searchInNode(node.getChildren().get(i), key);
    }
}
