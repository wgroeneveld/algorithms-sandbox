package be.brainbaking.datastructures.trees;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class NodeTest {

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
