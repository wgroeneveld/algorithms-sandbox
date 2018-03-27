package be.brainbaking.datastructures.hashing;

public class QuadraticProbeHash implements Hashable {

    private final int max;

    public QuadraticProbeHash(int max) {
        this.max = max;
    }

    @Override
    public int hash(Object key, int probeStep) {
        return (key.hashCode() + probeStep * probeStep) % max;
    }
}
