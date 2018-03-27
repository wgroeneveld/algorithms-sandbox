package be.brainbaking.datastructures.hashtable;

public class HashBucket<Key, Value> implements IAmBucketable<Key, Value> {

    /**
     * the key of the bucket to store the value in
     */
    private final Key key;

    /**
     * "sattelite" data
     */
    private final Value value;

    public Value getValue() {
        return value;
    }

    public Key getKey() {
        return key;
    }

    public HashBucket(Key key, Value value) {
        this.key = key;
        this.value = value;
    }
}
