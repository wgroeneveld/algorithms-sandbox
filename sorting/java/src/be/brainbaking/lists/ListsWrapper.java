package be.brainbaking.lists;

import java.util.List;

public class ListsWrapper {

    private final int oneBasedA;
    private int oneBasedB;

    public ListsWrapper with(int oneBasedB) {
        this.oneBasedB = oneBasedB;
        return this;
    }

    public void in(List<Integer> list) {
        Lists.swap(list, oneBasedA, oneBasedB);
    }

    public ListsWrapper(int oneBasedA) {
        this.oneBasedA = oneBasedA;
    }
}
