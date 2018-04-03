package be.brainbaking.datastructures.trees;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BTreeTest {

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
