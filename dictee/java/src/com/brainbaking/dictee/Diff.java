package com.brainbaking.dictee;

public class Diff {

    private final char aChar;
    private final int refIndex;
    private final int score;

    public int getScore() {
        return score;
    }

    private Diff(char aChar, int index, int score) {
        this.aChar = aChar;
        this.refIndex = index;
        this.score = score;
    }

    public static Diff create(char aChar) {
        return create(aChar, -1);
    }

    public static Diff hoofdletter(char aChar, int index) {
        return new Diff(aChar, index, 1);
    }

    public static Diff create(char aChar, int index) {
        return new Diff(aChar, index, 2);
    }

    @Override
    public String toString() {
        return aChar + "@" + refIndex + "-" + score;
    }
}
