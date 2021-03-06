package be.brainbaking.sorting;

import be.brainbaking.lists.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static be.brainbaking.lists.Lists.asString;

public class HeapSort implements Sortable {

    /**
     * heap-size[A] <- length[A]
     * for i <- |length[A]/2| downto 1
     *   do MAX-HEAPIFY(A, i)
     */
    protected List<Integer> buildMaxHeap(List<Integer> list) {
        System.out.println("to maxify: " + asString(list));
        List<Integer> heapList = new ArrayList<>(list);
        int halfIndex = (int)Math.floor(list.size() / 2);

        for(int i =  halfIndex; i >= 1; i--) {
            maxHeapify(heapList, i);
        }

        System.out.println("maxified: " + asString(heapList));
        return heapList;
    }

    private int leftIndex(int index) {
        return index * 2;
    }

    private int rightIndex(int index) {
        return leftIndex(index) + 1;
    }

    private int parentIndex(int index) {
        return (int)Math.floor(index / 2);
    }

    private void maxHeapify(List<Integer> list, int oneBasedIndex) {
        int lefti = leftIndex(oneBasedIndex);
        int righti = rightIndex(oneBasedIndex);
        int largesti = oneBasedIndex;

        if(lefti <= list.size() && list.get(lefti - 1) > list.get(oneBasedIndex - 1)) {
            largesti = lefti;
        }
        if(righti <= list.size() && list.get(righti - 1) > list.get(largesti - 1)) {
            largesti = righti;
        }

        if(largesti != oneBasedIndex) {
            Lists.swap(list, oneBasedIndex, largesti);
            maxHeapify(list, largesti);
        }
    }

    /**
     * BUILD-MAX-HEAP(A)
     * for i <- length[A] downto 2
     *   do exchange A[1] with A[i]
     *   heap-size[A] <- heap-size[A] - 1
     *   MAX-HEAPIFY(A, 1)
     */
    @Override
    public List<Integer> sort(List<Integer> list) {
        List<Integer> heap = buildMaxHeap(list);
        List<Integer> sorted = new ArrayList<>();

        for(int i = heap.size(); i >= 1; i--) {
            // in plaats van te swappen met 1, i pitsen we het hoofd van de tree er af
            // ik wil niet heap-size manipuleren en doorgeven
            // !! Vergeet niet dat dit O(n!) tijd toevoegt aan ons algoritme
            sorted.add(0, heap.get(0));
            heap.remove(0);
            maxHeapify(heap, 1);
        }

        System.out.println("sorted: " + asString(sorted));
        return sorted;
    }
}
