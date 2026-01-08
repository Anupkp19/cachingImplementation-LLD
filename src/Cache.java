public interface Cache<K,V> {
    V get(K key) throws InvalidCapacityException;
    void put(K key, V value);
}
