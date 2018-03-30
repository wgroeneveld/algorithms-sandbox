package com.brianbaking.dynamic.fibo;

import java.util.ArrayList;
import java.util.List;

public class NaiveFibonacci implements Fiboable {

    public List<Integer> calculate(int count) {
        List<Integer> nrs = new ArrayList<>();
        nrs.add(0);
        nrs.add(1);

        for(int i = 2; i <= count; i++) {
            nrs.add(nrs.get(i - 1) + nrs.get(i - 2));
        }

        return nrs;
    }
}
