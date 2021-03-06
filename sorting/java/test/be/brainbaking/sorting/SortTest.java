package be.brainbaking.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SortTest {

    @Test
    public void bucketSort() {
        simpleTestCaseFor(new BucketSort());
    }

    @Test
    public void quickSort() {
        simpleTestCaseFor(new QuickSort());
    }

    @Test
    public void heapSort() {
        simpleTestCaseFor(new HeapSort());
    }

    @Test
    public void insertionSort() {
        simpleTestCaseFor(new InsertionSort());
    }

    private void simpleTestCaseFor(Sortable sorter) {
        List<Integer> result = sorter.sort(Arrays.asList(4, 2, 3, 1, 6, 5));

        Assertions.assertArrayEquals(Arrays.asList(1, 2, 3, 4, 5, 6).toArray(), result.toArray());
    }
}
