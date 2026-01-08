import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class FifoCache<K,V> implements Cache <K,V>{
    private final int capacity;
    private final ConcurrentHashMap<K,V> map;
    private final Queue<K> keyColl;

    public FifoCache(int capacity) {
        this.capacity = capacity;
        this.map=new ConcurrentHashMap<>(capacity);
        this.keyColl=new LinkedList<>();
    }


    @Override
    public V get(K key) throws InvalidCapacityException {
        if(getValue(key) == null){
            throw  new InvalidCapacityException("Key does not exist");
        }
        return getValue(key);
    }
    private synchronized V getValue(K key) {
        return map.get(key);
    }

    @Override
    public void put(K key, V value) {
        if(!map.containsKey(key)) {
            if(map.size()==capacity){
                K lastKey = keyColl.poll();
                keyColl.offer(lastKey);
                map.remove(lastKey);
            }
            keyColl.add(key);

        }
        map.put(key, value);
    }
}
