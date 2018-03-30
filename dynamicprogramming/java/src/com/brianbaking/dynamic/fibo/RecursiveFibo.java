package com.brianbaking.dynamic.fibo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class RecursiveFibo implements Fiboable {

    @Override
    public List<Integer> calculate(int count) {
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(0, 1));
        calculateRecursively(2, count, list);
        return list;
    }

    private void calculateRecursively(int index, int count, List<Integer> list) {
        if(index <= count) {
            list.add(list.get(index - 1) + list.get(index - 2));
            calculateRecursively(index + 1, count, list);
        }
    }
}
