package com.brainbaking.dynamic.fibo;

import com.brianbaking.dynamic.fibo.Fiboable;
import com.brianbaking.dynamic.fibo.NaiveFibonacci;
import com.brianbaking.dynamic.fibo.RecursiveFibo;
import org.junit.Test;

import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;

public class FibonacciTest {

    @Test
    public void calculateNaive() {
        testFibo(new NaiveFibonacci());
    }

    @Test
    public void calculateRecursively() {
        testFibo(new RecursiveFibo());
    }

    private void testFibo(Fiboable fibo) {
        String result = fibo.calculate(10).stream().map(i -> i.toString()).collect(Collectors.joining(","));

        assertEquals("0,1,1,2,3,5,8,13,21,34,55", result);
    }

}
