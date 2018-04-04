package be.brainbaking.datastructures.trees;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class NodeTest {

    @Test
    public void merge_addsKeysAndChildren() {
        Node child = new Node(true);
        child.addKey("I");

        Node node = new Node(false);
        node.addKey("G");
        node.addKey("H");
        node.addChild(new Node(true));

        Node siblingToMergeWith = new Node(false);
        siblingToMergeWith.addKey("K");
        siblingToMergeWith.addKey("L");
        Node siblingChild = new Node(true);
        siblingChild.addKey("X");
        siblingToMergeWith.addChild(siblingChild);

        node.mergeWith(siblingToMergeWith);

        assertArrayEquals(Arrays.asList("G", "H", "K", "L").toArray(), node.getKeys().toArray());
        assertEquals(2, node.getChildren().size());
    }

    @Test
    public void getKeyBetweenChildren_basedOnChildIndex() {
        Node parent = new Node(false);
        parent.addKey("J");
        parent.addKey("M");

        Node child1 = new Node(true);
        child1.addKey("G");
        child1.addKey("H");
        parent.addChild(child1);
        Node child2 = new Node(true);
        child2.addKey("K");
        child2.addKey("L");
        parent.addChild(child2);
        Node child3 = new Node(true);
        child3.addKey("O");
        child3.addKey("P");
        child3.addKey("R");
        parent.addChild(child3);

        assertEquals("J", parent.getKeyBetweenChildren(child1, child2));
        assertEquals("M", parent.getKeyBetweenChildren(child2, child3));
    }

    @Test
    public void getExtremeKey_returnsFirstKeyBiggerThanParentKey_leftSide() {
        Node parent = new Node(false);
        parent.addKey("J");
        parent.addKey("M");

        Node child = new Node(true);
        child.addKey("O");
        child.addKey("P");
        child.addKey("R");
        parent.addChild(child);

        assertEquals("O", child.getExtremeKeyComparedTo("M"));
    }

    @Test
    public void getExtremeKey_returnsLastKeySmallerThanParentKey_rightSide() {
        Node parent = new Node(false);
        parent.addKey("J");
        parent.addKey("M");

        Node child = new Node(true);
        child.addKey("K");
        child.addKey("L");
        parent.addChild(child);

        assertEquals("L", child.getExtremeKeyComparedTo("M"));
    }

    @Test
    public void createFromSplitResult_nodeALeftAndBRight() {
        Node root = new Node(false);
        root.addKey("R");

        Node node = new Node(true);
        node.addKey("A");

        NodeSplitResult result = new NodeSplitResult(node, "B");
        Node newRoot = Node.createFromSplitResult(root, result);

        assertSame(root, newRoot.getChildren().get(0));
        assertSame(node, newRoot.getChildren().get(1));
    }

    @Test
    public void addKey_addsToCorrectPositionAutomatically() {
        Node node = new Node(true);
        node.addKey("A");
        node.addKey("C");
        node.addKey("B");

        assertArrayEquals(Arrays.asList("A", "B", "C").toArray(), node.getKeys().toArray());
    }

    @Test
    public void split() {

        Node node = new Node(false);
        Node child = new Node(true);

        node.addChild(child);
        node.addKey("N");
        node.addKey("W");

        child.addKey("P");
        child.addChild(new Node(true));
        child.addKey("Q");
        child.addChild(new Node(true));
        child.addKey("R");
        child.addChild(new Node(true));
        child.addKey("S");
        child.addChild(new Node(true));
        child.addKey("T");
        child.addChild(new Node(true));
        child.addKey("U");
        child.addChild(new Node(true));
        child.addKey("V");
        child.addChild(new Node(true));

        NodeSplitResult result = child.split(4);

        assertEquals(3, result.getNewNode().getNumberOfKeys());
        assertEquals(3, child.getNumberOfKeys());
        assertEquals("S", result.getSplitKey());
        assertArrayEquals(Arrays.asList("P", "Q", "R").toArray(), child.getKeys().toArray());
        assertArrayEquals(Arrays.asList("T", "U", "V").toArray(), result.getNewNode().getKeys().toArray());
    }
}
