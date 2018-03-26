package be.brainbaking.lists;

public class SomeLinkedObject<T> {

    private final SomeLinkedObject<T> next;
    private final T key;

    public SomeLinkedObject<T> getNext() {
        return next;
    }

    @Override
    public String toString() {
        return "[" + key + "]";
    }

    public T getKey() {
        return key;
    }

    public SomeLinkedObject(T key) {
        this(null, key);
    }

    public SomeLinkedObject(SomeLinkedObject<T> next, T key) {
        this.next = next;
        this.key = key;
    }
}
