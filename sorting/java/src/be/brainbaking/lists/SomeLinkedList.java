package be.brainbaking.lists;

import java.util.ArrayList;
import java.util.List;

public class SomeLinkedList<T> {

    private SomeLinkedObject<T> head;

    public SomeLinkedList() {
    }

    public SomeLinkedList(T singleItem) {
        add(singleItem);
    }

    public List<T> toList() {
        List<T> theList = new ArrayList<>();
        SomeLinkedObject<T> curr = head;
        for(int i = 0; i < size(); i++) {
            if(curr == null) {
                return theList;
            }

            theList.add(curr.getKey());
            curr = curr.getNext();
        }
        return theList;
    }

    public int size() {
        SomeLinkedObject<T> curr = head;
        int theSize = 0;
        while(curr != null) {
            theSize++;
            curr = curr.getNext();
        }
        return theSize;
    }

    public T get(int index) {
        SomeLinkedObject<T> curr = head;
        for (int i = 0; i < index; i++) {
            if (curr == null) {
                return null;
            }

            curr = curr.getNext();
        }
        return curr == null ? null : curr.getKey();
    }

    public void add(T key) {
        if(head == null) {
            head = new SomeLinkedObject<>(key);
        } else {
            head = new SomeLinkedObject<>(new SomeLinkedObject<>(key), head.getKey());
        }
    }
}
