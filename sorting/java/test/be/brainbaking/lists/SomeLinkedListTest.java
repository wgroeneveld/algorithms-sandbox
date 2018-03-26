package be.brainbaking.lists;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SomeLinkedListTest {

    @Test
    public void size_newList_isZero() {
        SomeLinkedList<Integer> list = new SomeLinkedList<>();

        assertEquals(0, list.size());
    }

    @Test
    public void add_increasesSize() {
        SomeLinkedList<Integer> list = new SomeLinkedList<>();
        list.add(123);
        list.add(456);

        assertEquals(2, list.size());
    }

    @Test
    public void get_getsByIndex_WithinRange() {
        SomeLinkedList<Integer> list = new SomeLinkedList<>();
        list.add(123);
        list.add(456);

        assertEquals(123, (int) list.get(0));
        assertEquals(456, (int) list.get(1));
    }

    @Test
    public void get_indexOutOfRange_ReturnsNull_endOfRange() {
        SomeLinkedList<Integer> list = new SomeLinkedList<>();
        list.add(123);
        list.add(456);

        assertEquals(null, list.get(2));
    }

    @Test
    public void get_indexOutOfRange_ReturnsNull_emptyList() {
        SomeLinkedList<Integer> list = new SomeLinkedList<>();

        assertEquals(null, list.get(0));
    }

}
