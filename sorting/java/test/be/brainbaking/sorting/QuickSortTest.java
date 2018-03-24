package be.brainbaking.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSortTest {

    @Test
    public void partition_accordingToR() {
        QuickSort sort = new QuickSort();
        List<Integer> toPartition = Arrays.asList(2, 8, 7, 1, 3, 5, 6, 4);
        List<Integer> partitioned = new ArrayList<>(toPartition);

        sort.partition(partitioned, 1, toPartition.size());

        Assertions.assertArrayEquals(partitioned.toArray(), Arrays.asList(2, 1, 3, 4, 7, 5, 6, 8).toArray());
    }
}
