package be.brainbaking.datastructures.trees;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

// deletion examples: https://www.youtube.com/watch?v=fKubKYzwDl0 - cases omgekeerde van p451
public class BTreeTest {

    @Test
    public void deleteCase1() {
        Node root = new Node(false);
        root.addKey("D");
        root.addKey("G");

        Node child1 = new Node(true);
        child1.addKey("A");
        child1.addKey("B");
        child1.addKey("C");

        Node child2 = new Node(true);
        child2.addKey("E");
        child2.addKey("F");

        Node child3 = new Node(true);
        child3.addKey("H");
        child3.addKey("I");

        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);

        BTree tree = new BTree(root, 3);
        tree.delete("B");

        assertArrayEquals(Arrays.asList("A", "B").toArray(), tree.getRoot().getChildren().get(0).getKeys().toArray());
    }

    @Test
    public void deleteCase2A() {
        Node root = new Node(false);
        root.addKey("Q");
        root.addKey("U");

        Node child1 = new Node(true);
        child1.addKey("O");
        child1.addKey("P");

        Node child2 = new Node(true);
        child2.addKey("R");
        child2.addKey("S");
        child2.addKey("T");

        Node child3 = new Node(true);
        child3.addKey("W");
        child3.addKey("X");

        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);

        BTree tree = new BTree(root, 3);
        tree.delete("U");

        assertArrayEquals(Arrays.asList("Q", "T").toArray(), tree.getRoot().getKeys().toArray());
        assertArrayEquals(Arrays.asList("O", "P").toArray(), tree.getRoot().getChildren().get(0).getKeys().toArray());
        assertArrayEquals(Arrays.asList("R", "S").toArray(), tree.getRoot().getChildren().get(1).getKeys().toArray());
        assertArrayEquals(Arrays.asList("W", "X").toArray(), tree.getRoot().getChildren().get(2).getKeys().toArray());
    }

    @Test
    public void deleteCase2B() {
        Node root = new Node(false);
        root.addKey("I");
        root.addKey("M");

        Node child1 = new Node(true);
        child1.addKey("G");
        child1.addKey("H");

        Node child2 = new Node(true);
        child2.addKey("J");
        child2.addKey("K");
        child2.addKey("L");

        Node child3 = new Node(true);
        child3.addKey("O");
        child3.addKey("P");

        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);

        BTree tree = new BTree(root, 3);
        tree.delete("I");

        assertArrayEquals(Arrays.asList("J", "M").toArray(), tree.getRoot().getKeys().toArray());
        assertArrayEquals(Arrays.asList("G", "H").toArray(), tree.getRoot().getChildren().get(0).getKeys().toArray());
        assertArrayEquals(Arrays.asList("K", "L").toArray(), tree.getRoot().getChildren().get(1).getKeys().toArray());
        assertArrayEquals(Arrays.asList("O", "P").toArray(), tree.getRoot().getChildren().get(2).getKeys().toArray());
    }

    @Test
    public void deleteCase2C() {
        Node root = new Node(false);
        root.addKey("R");
        root.addKey("U");
        root.addKey("X");

        Node child1 = new Node(true);
        child1.addKey("P");
        child1.addKey("Q");

        Node child2 = new Node(true);
        child2.addKey("S");
        child2.addKey("T");

        Node child3 = new Node(true);
        child3.addKey("V");
        child3.addKey("W");

        Node child4 = new Node(true);
        child4.addKey("Y");
        child4.addKey("Z");

        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);
        root.addChild(child4);

        BTree tree = new BTree(root, 3);
        tree.delete("U");

        assertArrayEquals(Arrays.asList("R", "X").toArray(), tree.getRoot().getKeys().toArray());
        assertArrayEquals(Arrays.asList("P", "Q").toArray(), tree.getRoot().getChildren().get(0).getKeys().toArray());
        assertArrayEquals(Arrays.asList("S", "T", "V", "W").toArray(), tree.getRoot().getChildren().get(1).getKeys().toArray());
        assertArrayEquals(Arrays.asList("Y", "Z").toArray(), tree.getRoot().getChildren().get(2).getKeys().toArray());
    }

    @Test
    public void deleteCase3A() {
        Node root = new Node(false);
        root.addKey("J");
        root.addKey("M");

        Node child1 = new Node(true);
        child1.addKey("G");
        child1.addKey("H");

        Node child2 = new Node(true);
        child2.addKey("K");
        child2.addKey("L");

        Node child3 = new Node(true);
        child3.addKey("O");
        child3.addKey("P");
        child3.addKey("R");

        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);

        BTree tree = new BTree(root, 3);
        tree.delete("L");

        assertArrayEquals(Arrays.asList("J", "O").toArray(), tree.getRoot().getKeys().toArray());
        assertArrayEquals(Arrays.asList("K", "M").toArray(), tree.getRoot().getChildren().get(1).getKeys().toArray());
    }

    @Test
    public void deleteCase3B() {
        Node root = new Node(false);
        root.addKey("Q");
        root.addKey("T");

        Node child1 = new Node(true);
        child1.addKey("O");
        child1.addKey("P");

        Node child2 = new Node(true);
        child2.addKey("R");
        child2.addKey("S");

        Node child3 = new Node(true);
        child3.addKey("W");
        child3.addKey("X");

        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);

        BTree tree = new BTree(root, 3);
        tree.delete("S");

        assertArrayEquals(Arrays.asList("Q").toArray(), tree.getRoot().getKeys().toArray());
        assertArrayEquals(Arrays.asList("O", "P").toArray(), tree.getRoot().getChildren().get(0).getKeys().toArray());
        assertArrayEquals(Arrays.asList("R", "T", "W", "X").toArray(), tree.getRoot().getChildren().get(1).getKeys().toArray());
    }

    @Test
    public void searching_afterAddingExampleAndHavingToSplit() {
        BTree tree = new BTree(4);

        tree.add("A");
        tree.add("D");
        tree.add("F");
        tree.add("H");
        tree.add("L");
        tree.add("N");
        tree.add("P");
        tree.add("Q");

        assertEquals(1, tree.search("D").getIndex());
        assertEquals(0, tree.search("H").getIndex());  // want die key is naar boven verhuisd bij splitten
        assertEquals(2, tree.search("P").getIndex());
        assertEquals(3, tree.search("Q").getIndex());
    }

    @Test
    public void addingOnly() {
        BTree tree = new BTree(4);

        tree.add("A");
        tree.add("D");
        tree.add("F");
        tree.add("H");
        tree.add("L");
        tree.add("N");
        tree.add("P");

        // nog eentje toevoegen zou moeten splitsen (4*2 groot)
        tree.add("Q");

        assertArrayEquals(Arrays.asList("H").toArray(), tree.getRoot().getKeys().toArray());
        assertArrayEquals(Arrays.asList("A", "D", "F").toArray(), tree.getRoot().getChildren().get(0).getKeys().toArray());
        assertArrayEquals(Arrays.asList("L", "N", "P", "Q").toArray(), tree.getRoot().getChildren().get(1).getKeys().toArray());
    }

    @Test
    public void simpleOperations_addAndSearch() {
        BTree tree = new BTree(5);
        tree.add("key1");

        BTreeSearchResult result = tree.search("key1");
        assertEquals(true, result.isFound());
    }

    @Test
    public void simpleOperations_searchKeyNotFound() {
        BTree tree = new BTree(5);
        tree.add("key100");

        BTreeSearchResult result = tree.search("key1");
        assertEquals(false, result.isFound());
    }

}
