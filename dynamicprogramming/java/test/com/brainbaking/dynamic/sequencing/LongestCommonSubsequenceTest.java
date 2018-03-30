package com.brainbaking.dynamic.sequencing;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LongestCommonSubsequenceTest {

    @Test
    public void longestCommonSubSequence() {
        LongestCommonSubsequence sequencer = new LongestCommonSubsequence();

        String result = sequencer.calculate("ABCBDAB", "BDCABA");

        assertEquals("BCBA", result);
    }

}
