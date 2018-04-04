package be.brainbaking.datastructures.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /**
     * Voegt key gesorteerd toe.
     * Dit heb ik enkel voor bepaalde gevallen nodig, maar gebruik ik hier gegeneraliseerd.
     * Betekent een performance hit van worst-case O(n[x]) (bvb switchkeys) -> uit context af te leiden waar
     * @param key
     */
    public void addKey(String key) {
        int i;
        for(i = 0; i < keys.size(); i++) {
            if(keys.get(i).compareTo(key) >= 0) {
                break;
            }
        }

        keys.add(i, key);
    }

    public List<Node> getChildenBetweenKey(String key) {
        int keyIndex = keys.indexOf(key);
        return Arrays.asList(children.get(keyIndex), children.get(keyIndex + 1));   // kan volgens specs niet crashen, zie def. BTree props
    }

    public String getKeyBetweenChildren(Node child1, Node child2) {
        return keys.get(Math.max(children.indexOf(child1), children.indexOf(child2)) - 1);
    }

    public String getExtremeKeyComparedTo(String parentKey) {
        return parentKey.compareTo(keys.get(0)) < 0 ? keys.get(0) : keys.get(keys.size() - 1);
    }

    public List<Node> getSiblingsOf(Node child) {
        List<Node> siblings = new ArrayList<>();
        int index = children.indexOf(child);
        if(index > 0) {
            siblings.add(children.get(index - 1));
        }
        if(index < children.size() - 1) {
            siblings.add(children.get(index + 1));
        }

        return siblings;
    }

    public void mergeWith(Node node) {
        if(node.getKeys().get(0).compareTo(getKeys().get(0)) >= 0) {
            rightSideMergeWith(node);
        } else {
            leftSideMergeWith(node);
        }
    }

    private void leftSideMergeWith(Node node) {
        keys = Stream.concat(node.keys.stream(), keys.stream()).collect(Collectors.toList());
        children = Stream.concat(node.children.stream(), children.stream()).collect(Collectors.toList());
    }

    private void rightSideMergeWith(Node node) {
        keys = Stream.concat(keys.stream(), node.keys.stream()).collect(Collectors.toList());
        children = Stream.concat(children.stream(), node.children.stream()).collect(Collectors.toList());
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
        // n[x] = aantal sleutels = size(c[x]) - 1; er zijn altijd n[x] + 1 kinderen
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

    public String getLastKey() {
        return keys.get(getNumberOfKeys() - 1);
    }

    public String getFirstKey() {
        return keys.get(0);
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

    public void deleteKey(String key) {
        keys.remove(key);
    }
}
