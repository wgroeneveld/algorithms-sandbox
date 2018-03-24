package be.brainbaking.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class HeapSortTest {

    @Test
    public void heapSort_withBiggerHeap() {
        HeapSort sort = new HeapSort();

        List<Integer> expetedSort = Arrays.asList(1, 2, 3, 4, 7, 8, 9, 10, 14, 16);
        List<Integer> actualSort = sort.sort(Arrays.asList(4, 1, 3, 2, 16, 9, 10, 14, 8, 7));

        Assertions.assertArrayEquals(expetedSort.toArray(), actualSort.toArray());
    }

    @Test
    public void heapSort_buildMaxHeap() {
        HeapSort sort = new HeapSort();

        List<Integer> expectedHeap = Arrays.asList(16, 14, 10, 8, 7, 9, 3, 2, 4, 1);
        List<Integer> maxHeap = sort.buildMaxHeap(Arrays.asList(4, 1, 3, 2, 16, 9, 10, 14, 8, 7));

        Assertions.assertArrayEquals(expectedHeap.toArray(), maxHeap.toArray());
    }
}
