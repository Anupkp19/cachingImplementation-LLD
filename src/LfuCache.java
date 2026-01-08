import java.util.HashMap;

public class LfuCache<K,V> implements Cache<K,V>{

private final int  capacity;
private final LfuFrequencyList lfuFrequencyList;
private final HashMap<K, LfuNode<K,V>> map;

    public LfuCache(int capacity) throws InvalidCapacityException {
        if(capacity==0) throw new InvalidCapacityException("Capacity cannot be zero");

        this.capacity = capacity;
        this.lfuFrequencyList=new LfuFrequencyList<>();
        this.map=new HashMap<>();
    }


    @Override
    public V get(K key) {
        System.out.println(map.toString());
        return getValue(key);
    }

    private synchronized V getValue(K key) {
        LfuNode<K,V> node = map.get(key);
        if(node==null) return null;
        lfuFrequencyList.removeNode(node);
        node.increaseFreq();
        lfuFrequencyList.addNode(node);
        return node.value;
    }

    @Override
    public void put(K key, V value) {
       putKeyValue(key,value);
    }

    private synchronized void putKeyValue(K key, V value) {
        if(map.containsKey(key)) {
            LfuNode<K,V> node = map.get(key);
            if(node==null) return;
            node.value=value;
            get(key);
            return;

        } else if(map.size()==capacity) {
            LfuNode lastNode = lfuFrequencyList.removeMinFreq();
            if(lastNode==null) return;
            map.remove(lastNode.key);
        }

        LfuNode<K,V> newNode = new LfuNode<>(key, value);
        map.put(key, newNode);
        lfuFrequencyList.addNode(newNode);
    }
}
