package be.brainbaking.datastructures.hashing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinearProbeHashTest {

    @Test
    public void shouldHashingBeRecalculated_ifMaximumChanges() {
        LinearProbeHash hash10 = new LinearProbeHash(10);
        LinearProbeHash hash15 = new LinearProbeHash(15);

        int hashed10 = hash10.hash((Integer) 123, 0);
        int hashed15 = hash15.hash((Integer) 123, 0);

        assertEquals(hashed15, hashed10);

        int hashed10Bigger = hash10.hash((Integer) 123456789, 0);
        int hashed15Bigger = hash15.hash((Integer) 123456789, 0);

        assertEquals(hashed15Bigger, hashed10Bigger);
    }

}
