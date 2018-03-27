package be.brainbaking.datastructures.hashtable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashTableTest {

    @Test
    public void add_increasesSize_andAbleToGetValue() {
        HashTable<Integer, Integer> table = new HashTable<>();
        table.put(123, 445);

        assertEquals(1, table.size());
        assertEquals(445, (int) table.get(123));
    }

}
