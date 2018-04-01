package com.brainbaking.greedy;

import com.com.brainbaking.greedy.Huffman;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class HuffmanTest {

    @Test
    public void huffman_OnlyOnCharacter() {
        Huffman huffman = new Huffman("aaa");

        String result = huffman.encode();

        assertEquals("000", result);
    }

    @Test
    public void huffman_charAFrequences5TimesButBAndCOnlyOnce() {
        Huffman huffman = new Huffman("baaaaac");

        String result = huffman.encode();

        assertEquals("001111101", result);
    }

    @Test
    public void huffmanEncoded() {
        Huffman huffman = new Huffman("hallo dit is een test wasda jong");

        String result = huffman.encode();

        assertEquals("110001101100110011000111101110100101111010000111001001011011101000100001011111001110100010111101111011111000011001110", result);
    }
}
