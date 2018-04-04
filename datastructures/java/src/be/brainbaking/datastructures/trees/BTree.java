package be.brainbaking.datastructures.trees;

import java.util.List;
import java.util.Optional;

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
        Node parent = searchResult.getParent();

        if(node.isLeaf() && node.getNumberOfKeys() >= t) {              // case 1
            node.deleteKey(key);
        } else if(node.isLeaf() && node.getNumberOfKeys() == t - 1) {   // case 3
            List<Node> siblings = parent.getSiblingsOf(node);

            Optional<Node> maybeSibling = getSiblingWithTNumberOfKeys(siblings);
            if(maybeSibling.isPresent()) {
                // 3.a
                String parentKeyToPushDown = parent.getKeyBetweenChildren(node, maybeSibling.get());
                String extremeKeyToPushUp = maybeSibling.get().getExtremeKeyComparedTo(parentKeyToPushDown);

                pushKeyDown(node, parent, parentKeyToPushDown);
                pullKeyUp(parent, maybeSibling, extremeKeyToPushUp);

                node.deleteKey(key);
            } else if(siblings.stream().allMatch(s -> s.getNumberOfKeys() == t - 1)) {
                // 3.b
                Node rightMostSibling = siblings.stream().filter(s -> s.getNumberOfKeys() == t - 1).reduce((one, two) -> two).get();

                node.mergeWith(rightMostSibling);
                String parentKeyToPushDown = parent.getKeyBetweenChildren(node, rightMostSibling);

                parent.getChildren().remove(rightMostSibling);
                pushKeyDown(node, parent, parentKeyToPushDown);

                node.deleteKey(key);
            }
        } else if(!node.isLeaf()) {                                     // case 2
            List<Node> children = node.getChildenBetweenKey(key);
            Node left = children.get(0);
            Node right = children.get(1);

            if(left.getNumberOfKeys() >= t) {
                // 2.a
                switchKeys(node, key, left, left.getLastKey());
                left.deleteKey(key);
            } else if(right.getNumberOfKeys() >= t) {
                // 2.b
                switchKeys(node, key, right, right.getFirstKey());
                right.deleteKey(key);
            } else {
                // 2.c
                left.mergeWith(right);
                node.getChildren().remove(right);

                node.deleteKey(key);
            }
        }
    }

    private void switchKeys(Node node, String key, Node nodeToSwitch, String keyToSwith) {
        node.getKeys().remove(key);
        node.addKey(keyToSwith);
        nodeToSwitch.getKeys().remove(keyToSwith);
        nodeToSwitch.addKey(key);
    }

    private void pullKeyUp(Node parent, Optional<Node> maybeSibling, String extremeKeyToPushUp) {
        maybeSibling.get().getKeys().remove(extremeKeyToPushUp);
        parent.addKey(extremeKeyToPushUp);
    }

    private void pushKeyDown(Node node, Node parent, String parentKeyToPushDown) {
        parent.getKeys().remove(parentKeyToPushDown);
        node.addKey(parentKeyToPushDown);
    }


    private Optional<Node> getSiblingWithTNumberOfKeys(List<Node> siblings) {
        return siblings.stream().filter(s -> s.getNumberOfKeys() == t).findFirst();
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
