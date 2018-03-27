package be.brainbaking.datastructures.hashing;

public class DoubleProbeHash implements Hashable {

    private final int max;

    public DoubleProbeHash(int max) {
        this.max = max;
    }

    private int auxiliarHash(Object key, int probeStep) {
        return (1 + key.hashCode()) * (max - 1);
    }

    @Override
    public int hash(Object key, int probeStep) {
        return (auxiliarHash(key, probeStep) + probeStep) % max;
    }
}
