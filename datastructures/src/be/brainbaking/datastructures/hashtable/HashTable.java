package be.brainbaking.datastructures.hashtable;

import be.brainbaking.datastructures.hashing.Hashable;
import be.brainbaking.datastructures.hashing.LinearProbeHash;

public class HashTable<T> {

    private final HashBucket<T>[] table;
    private final Hashable hasher;

    public HashTable() {
        this(10);
    }

    private HashTable(int initialSize) {
        table = new HashBucket[10];
        hasher = new LinearProbeHash(10);
    }

    public void add(T key) {
        add(key, 0);
    }

    public void remove(T key) {
        throw new UnsupportedOperationException();
        // TODO "deleted" stuff
    }

    public int search(T key) {
        throw new UnsupportedOperationException();
        // TODO return key if exists
    }

    private void add(T key, int probeStep) {
        if(probeStep > table.length) {
            throw new UnsupportedOperationException("unable to add key to table, everything occupied?");
        }

        int hash = hasher.hash(key, probeStep);
        if(table[hash] != null) {
            add(key, probeStep + 1);
        } else {
            table[hash] = new HashBucket<>(key);
        }

    }

}
