package be.brainbaking.datastructures.hashtable;

public class DeletedBucket<Key, Value> implements IAmBucketable<Key, Value> {

    private static DeletedBucket bucket = new DeletedBucket();

    public static <Key, Value> DeletedBucket<Key, Value> singleton() {
        return bucket;
    }

}
