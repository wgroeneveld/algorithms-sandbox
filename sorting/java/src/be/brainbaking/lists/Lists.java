package be.brainbaking.lists;

import java.util.List;
import java.util.stream.Collectors;

public class Lists {

    public static ListsWrapper exchange(int oneBasedA) {
        return new ListsWrapper(oneBasedA);
    }

    public static void swap(List<Integer> list, int oneBasedA, int oneBasedB) {
        int temp = list.get(oneBasedA - 1);
        list.set(oneBasedA - 1, list.get(oneBasedB - 1));
        list.set(oneBasedB - 1, temp);
    }

    public static String asString(List<Integer> list) {
        return list.stream().map(i -> i.toString()).collect(Collectors.joining(", "));
    }

}
