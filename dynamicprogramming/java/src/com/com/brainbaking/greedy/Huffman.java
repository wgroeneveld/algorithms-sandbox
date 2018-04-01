package com.com.brainbaking.greedy;

import java.util.*;
import java.util.stream.Collectors;

public class Huffman {

    private final String toEncode;
    private final Node root;

    public Huffman(String toEncode) {
        this.toEncode = toEncode;

        root = buildMinRoot(buildQueue());
    }

    private Node buildMinRoot(Queue<Node> nodes) {
        Node root = null;

        while(nodes.size() > 1) {
            Node first = nodes.poll();
            Node second = nodes.poll();

            root = new Node(first, second);
            nodes.add(root);
        }

        // in geval van maar 1 karakter
        return root == null ? nodes.poll() : root;
    }

    private Queue<Node> buildQueue() {
        Set<Integer> chars = buildCharSet();
        Queue<Node> tree = new PriorityQueue<>();

        for(Integer character : chars) {
            int frequency = (int) toEncode.chars().filter(c -> c == character).count();
            tree.add(new Node(character, frequency));
        }
        return tree;
    }

    private Set<Integer> buildCharSet() {
        return toEncode.chars().boxed().collect(Collectors.toSet());
    }

    public String encode() {
        Map<Character, String> encodeMap = new HashMap<>();
        buildEncodedMap(root, encodeMap, "");

        String encoded = "";
        for(int i = 0; i < toEncode.length(); i++) {
            encoded += encodeMap.get(toEncode.charAt(i));
        }
        return encoded;

        // wat is hier mis mee?
        //return toEncode.chars().mapToObj(c -> encodeMap.get(c)).collect(Collectors.joining());
    }

    private void buildEncodedMap(Node node, Map<Character,String> map, String encoded) {
        if(node.isRoot()) {
            // in geval van maar 1 karakter
            map.put(node.getCharacter(), encoded.equals("") ? "0" : encoded);
            return;
        }

        buildEncodedMap(node.getLeft(), map, encoded + "0");
        buildEncodedMap(node.getRight(), map, encoded + "1");
    }

}
