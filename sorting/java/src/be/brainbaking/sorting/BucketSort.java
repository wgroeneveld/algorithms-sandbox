package be.brainbaking.sorting;

import be.brainbaking.lists.SomeLinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BucketSort implements Sortable {

    private final InsertionSort insertionSorter;

    public BucketSort() {
        this.insertionSorter = new InsertionSort();
    }

    /**
     * n <- length[A]
     * for i <- 1 to n
     *      do insert A[i] into list B[n*A[i]] rounded down
     * for i <- 0 to n - 1
     *      do sort list B[i] with insertion srot
     * concatenate lists B[0], B[1], ..., B[n - 1] together
     * @param list A
     * @return sorted list
     */
    @Override
    public List<Integer> sort(List<Integer> list) {
        int n = list.size();
        List<SomeLinkedList<Integer>> buckets = initializeBuckets(n);

        for(int i = 1; i <= n; i++) {
            int x = list.get(i - 1);
            int bucketIndex = calculateBucketIndex(n, x);

            if(buckets.size() <= bucketIndex) {
                buckets.add(bucketIndex, new SomeLinkedList<>(x));
            } else {
                buckets.get(bucketIndex).add(x);
            }
        }

        return mergeSortedBuckets(buckets);
    }

    private List<Integer> mergeSortedBuckets(List<SomeLinkedList<Integer>> buckets) {
        List<Integer> sorted = new ArrayList<>();
        for (SomeLinkedList<Integer> bucket : buckets) {
            // crap... I'm too lazy to rewrite insertion sort to use the linkedlist or to implement List<T>. Convert it!

            sorted.addAll(insertionSorter.sort(bucket.toList()));
        }

        return sorted;
    }

    private List<SomeLinkedList<Integer>> initializeBuckets(int n) {
        List<SomeLinkedList<Integer>> buckets = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            buckets.add(new SomeLinkedList<>());
        }

        return buckets;
    }

    /**
     * bucket sorting gaat uit van een uniforme distributie waarbij 0 <= A[i] <= 1
     * deel x door 10 in dit simpel voorbeeld.
     */
    private int calculateBucketIndex(int n, int x) {
        return (int)Math.floor(n * ((double) x / 10));
    }
}
