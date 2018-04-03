package be.brainbaking.datastructures.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Each node has the following fields:
 *  n[x], the number of keys
 *  the n[x] keys themselves, stored in nondecreasing order key1[x] <= key2[x] <= ... <= keyn[x]
 *  leaf[x], a boolean value that is TRUE if x is a leaf and FALSE if x is an internal node
 *
 *  each internal node x also contains n[x] + 1 pointers cn[x] to its children.
 *  The keys keyi[x] separate the ranges of keys stored in each subtree:
 *      if ki is any key stored in the subtree with root ci[x], then
 *      k1 <= key1[x] <= k2 <= key2[x]
 *
 * All leaves have the same depth, which is the tree's height h.
 * There are lower and upper bounds on the number of keys a node can contain. (fixed int, "t" >= 2)
 *  min. t - 1 keys
 *  max. 2t - 1 keys: "full"
 */
public class Node {

    private boolean leaf;
    private List<String> keys;
    private List<Node> children;

    public boolean isFull(int t) {
        return getNumberOfKeys() >= 2* t - 1;
    }

    public Node(boolean leaf) {
        keys = new ArrayList<>();
        children = new ArrayList<>();
        this.leaf = leaf;
    }

    public void addKey(String key) {
        int i;
        for(i = 0; i < keys.size(); i++) {
            if(keys.get(i).compareTo(key) >= 0) {
                break;
            }
        }

        keys.add(i, key);
    }

    public void addChild(Node node) {
        addChild(children.size(), node);
    }

    public void addChild(int index, Node node) {
        // dit lijkt mij nog een performance hit te zijn die niet in default in BTrees zit
        // ik wou het object-oriented aanpakken maar moet hier dan nog max O(index) aflopen.
        children.add(index, node);
    }

    public NodeSplitResult split(int t) {
        if(!isFull(t)) throw new UnsupportedOperationException("node niet vol, split zelf maar wa jong");

        Node newNode = new Node(isLeaf());

        for(int i = 0; i < t - 1; i++) {
            newNode.addKey(keys.get(i + t));
        }
        // ?? Aantal sleutels en aantal children kan niet gelijk zijn?
        if(!isLeaf()) {
            for(int i = 0; i < t; i++) {
                newNode.children.add(children.get(i + t - 1));
            }
        }

        List<String> newKeys = new ArrayList<>();
        for(int i = 0; i < t - 1; i++) {
            newKeys.add(keys.get(i));
        }
        String key = keys.get(t - 1);
        keys = newKeys;

        if(!isLeaf()) {
            List<Node> newChildren = new ArrayList<>();
            for(int i = 0; i < t; i++) {
                newChildren.add(children.get(i));
            }
            children = newChildren;
        }


        return new NodeSplitResult(newNode, key);
    }

    public static Node createRoot() {
        return new Node(true);
    }

    public int getNumberOfKeys() {
        return keys.size();
    }

    public List<Node> getChildren() {
        return children;
    }

    public List<String> getKeys() {
        return keys;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public static Node createFromSplitResult(Node node, NodeSplitResult splitResult) {
        Node root = new Node(false);

        root.addKey(splitResult.getSplitKey());
        root.addChild(node);
        root.addChild(splitResult.getNewNode());

        return root;
    }
}
