package be.brainbaking.datastructures.trees;

public class BTree {

    private Node root;
    private final int t;

    public BTree(Node node, int t) {
        this.root = node;
        this.t = t;
    }

    public BTree(int t) {
        this(Node.createRoot(), t);
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

    /**
     * case 1: if x is a leaf and x has >= t keys, just delete it.
     * case 2: if x is an internal node
     *  a) if x's left child has >= t keys, move the largest key to the key to delete.
     *  b) if x's right child has >= t keys, move the smallest key to the key to delete.
     *  c) if none of the children have >= t keys, merge the children and delete the key.
     * case 3: if x is a leaf and x has == t - 1 keys, then
     *  a) if x has a sibling with at least t keys
     *      - move parent's key -> x
     *      - find extreme(key) sibling with at least t keys (left/right), move x's key -> parent
     *      - then proceed as case 1
     *  b) if x's sibling also has t - 1 keys
     *      - merge x with sibling: move parent's key -> x (as t key)
     *      - then delete that key
     * @param key
     */
    public void delete(String key) {
        BTreeSearchResult searchResult = search(key);
        if(!searchResult.isFound()) return;
        Node node = searchResult.getNode();

        if(node.isLeaf() && node.getNumberOfKeys() >= t) {              // case 1
            node.deleteKey(key);
        } else if(node.isLeaf() && node.getNumberOfKeys() == t - 1) {   // case 3
            if(searchResult.getParent().getSiblingsOf(node).stream().anyMatch(s -> s.getNumberOfKeys() == t)) {
                // 3.a
            } else if(searchResult.getParent().getSiblingsOf(node).stream().allMatch(s -> s.getNumberOfKeys() == t - 1)) {
                // 3.b
            }
        } else if(!node.isLeaf()) {                                     // case 2
            if(node.getLeftChild().getNumberOfKeys() >= t) {
                // 2.a
            } else if(node.getRightChild().getNumberOfKeys() >= t) {
                // 2.b
            } else {
                // 2.c
            }
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
        return searchInNode(root, key, null);
    }

    private BTreeSearchResult searchInNode(Node node, String key, Node parent) {
        int i = 0;
        while(i < node.getNumberOfKeys() && key.compareTo(node.getKeys().get(i)) > 0) {
            i++;
        }
        if(i < node.getNumberOfKeys() && key == node.getKeys().get(i)) {
            return new BTreeSearchResult(node, i, parent);
        }

        if(node.isLeaf()) return new BTreeSearchResult();
        return searchInNode(node.getChildren().get(i), key, node);
    }
}
