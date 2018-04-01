package com.com.brainbaking.greedy;

public class Node implements Comparable<Node> {

    private final char character;
    private final int frequency;

    private Node left;
    private Node right;

    public boolean isRoot() {
        return left == null && right == null;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public char getCharacter() {
        return character;
    }

    public int getFrequency() {
        return frequency;
    }

    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
        this.character = '-';
        this.frequency = left.getFrequency() + right.getFrequency();
    }

    public Node(Integer character, int frequency) {
        this.character = (char) character.intValue();
        this.frequency = frequency;
    }

    @Override
    public int compareTo(Node o) {
        return frequency - o.frequency;
    }
}
