package be.brainbaking.sorting;

import be.brainbaking.lists.Lists;

import java.util.ArrayList;
import java.util.List;

import static be.brainbaking.lists.Lists.asString;
import static be.brainbaking.lists.Lists.exchange;

public class QuickSort implements Sortable {

    /**
     * quicksort met randomizer, anders wordt als partition index altijd A[r] gebruikt!
     * @param list
     * @return
     */
    @Override
    public List<Integer> sort(List<Integer> list) {
        List<Integer> sorted = new ArrayList<>(list);
        int i = (int)(Math.random() * list.size());
        int r = list.size();
        Lists.swap(sorted, i, r);

        quickSort(sorted, 1, sorted.size());
        return sorted;
    }

    /**
     * quicksort default
     * if p < r
     *      then q <- partition(A, p, r)
     *          quicksort(A, p, q - 1)
     *          quicksort(A, q + 1, r)
     * @param list to sort
     * @param oneBasedPartitionStart p
     * @param oneBasedPartitionEnd r
     * @return sorted list
     */
    private void quickSort(List<Integer> list, int oneBasedPartitionStart, int oneBasedPartitionEnd) {
        if(oneBasedPartitionStart >= oneBasedPartitionEnd) return;

        int q = partition(list, oneBasedPartitionStart, oneBasedPartitionEnd);
        quickSort(list, oneBasedPartitionStart, q - 1);
        quickSort(list, q + 1, oneBasedPartitionEnd);
    }

    /**
     * partition list so that each element of A[p..q-1] <= A[q] <= A[q + 1 .. r]
     * x <- A[r]
     * i <- p - 1
     * for j <- p to r - 1
     *      do if A[j] <= x
     *          then i <- i + 1
     *              exchange A[i] with A[j]
     * exchange A[i + 1] with A[r]
     * return i + 1
     * @param list A
     * @param oneBasedStartIndex p
     * @param oneBasedEndIndex r
     * @return i + 1 (partition index)
     */
    protected int partition(List<Integer> list, int oneBasedStartIndex, int oneBasedEndIndex) {
        System.out.println("to partition: " + asString(list));

        int x = list.get(oneBasedEndIndex - 1);
        int i = oneBasedStartIndex - 1;

        for(int j = oneBasedStartIndex; j <= oneBasedEndIndex - 1; j++) {
            if(list.get(j - 1) <= x) {
                i++;
                exchange(i).with(j).in(list);
            }
        }

        exchange(i + 1).with(oneBasedEndIndex).in(list);
        System.out.println("partitioned: " + asString(list));
        return i + 1;
    }
}
