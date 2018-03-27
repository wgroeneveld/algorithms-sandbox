package be.brainbaking.datastructures.hashtable;

import be.brainbaking.datastructures.hashing.Hashable;
import be.brainbaking.datastructures.hashing.LinearProbeHash;

import java.util.Arrays;

public class HashTable<Key, Value> {

    private final HashBucket<Key, Value>[] table;
    private final Hashable hasher;

    public HashTable() {
        this(10);
    }

    private HashTable(int initialSize) {
        table = new HashBucket[10];
        hasher = new LinearProbeHash(10);
    }

    public void put(Key key, Value value) {
        put(key, value, 0);
    }

    public Value remove(Key key) {
        throw new UnsupportedOperationException();
        // TODO "deleted" stuff (bestaande calls wijzigen)
    }

    public Value get(Key key) {
        return get(key, 0);
    }

    /**
     * Géén O(1), maar "gemiddeld" gezien wel, gegeven dat de hash goed gekozen is en we weinig moeten proben.
     * @param key key van bucket
     * @param probeStep probe start positie
     * @return indien niet gevonden, null, anders de bewaarde value
     */
    private Value get(Key key, int probeStep) {
        int hash = hasher.hash(key, probeStep);
        if(hash >= table.length) return null;


        if(table[hash].getKey() == key) return table[hash].getValue();
        return get(key, probeStep + 1);
    }

    /**
     * table.length is niet de "size". O(n) nodig, alles overlopen om te kijken wat ingevuld is... Kostelijk!
     * @return the size
     */
    public int size() {
        return (int) Arrays.stream(table).filter(entry -> entry != null).count();
    }

    private void put(Key key, Value value, int probeStep) {
        // TODO resizing van tabel indien te groot geworden
        int hash = hasher.hash(key, probeStep);
        if(hash >= table.length) {
            throw new UnsupportedOperationException("unable to add key to table, everything occupied?");
        }

        if(table[hash] != null) {
            put(key, value, probeStep + 1);
        } else {
            table[hash] = new HashBucket<>(key, value);
        }
    }

}
