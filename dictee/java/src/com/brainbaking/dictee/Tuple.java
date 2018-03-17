package com.brainbaking.dictee;

public class Tuple {
        private final Character character;
        private final int index;
        private final int extraMinpunt;

        public int getExtraMinpunt() {
            return extraMinpunt;
        }

        public static Tuple nietGevonden(int character) {
            return new Tuple(character, -1, 0);
        }

        public static Tuple hoofdletter(int character, int index) {
            return new Tuple(character, index, 1);
        }

        public boolean isChar(int chara) {
            return character == (char) chara;
        }

        public Tuple(int ch, int index) {
            this(ch, index, 0);
        }

        public boolean komtNietVoor() {
            return index < 0;
        }

    public int getIndex() {
        return index;
    }

    private Tuple(int character, int index, int extraMinpunt) {
            this.character = (char) character;
            this.index = index;
            this.extraMinpunt = extraMinpunt;
        }

        @Override
        public String toString() {
            return "(" + character + "," + index + ")";
        }
    }
