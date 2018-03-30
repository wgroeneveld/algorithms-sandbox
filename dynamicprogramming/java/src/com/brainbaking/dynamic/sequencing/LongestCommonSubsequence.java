package com.brainbaking.dynamic.sequencing;

public class LongestCommonSubsequence {

    public String calculate(String a, String b) {
        int[][] lengths = new int[a.length() + 1][b.length() + 1];
        char[] as = a.toCharArray();
        char[] bs = b.toCharArray();

        for(int i = 1; i <= a.length(); i++) {
            for(int j = 1; j <= b.length(); j++) {

                if(as[i - 1] == bs[j - 1]) {
                    lengths[i][j] = lengths[i - 1][j - 1] + 1;
                } else {
                    lengths[i][j] = Math.max(lengths[i][j - 1], lengths[i - 1][j]);
                }
            }
        }

        return retrace(lengths, as, as.length, bs.length);
    }

    private String retrace(int[][] lengths, char[] as, int i, int j) {
        if(i == 0 || j == 0) return "";

        int l = lengths[i][j];
        int ltop = lengths[i - 1][j];
        int lright = lengths[i][j - 1];

        if(ltop == l) {
            return retrace(lengths, as, i - 1, j);
        } else if(lright == l) {
            return retrace(lengths, as, i, j - 1);
        } else {
            return retrace(lengths, as, i - 1, j - 1) + as[i - 1];
        }

    }

}
