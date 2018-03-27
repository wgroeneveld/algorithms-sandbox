package be.brainbaking.datastructures.hashing;

public class LinearProbeHash implements Hashable {

    private final int max;

    public LinearProbeHash(int max) {
        this.max = max;
    }

    @Override
    public int hash(Object key, int probeStep) {
        return (key.hashCode() + probeStep) % max;
    }
}
