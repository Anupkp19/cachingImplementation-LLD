import java.util.concurrent.ConcurrentHashMap;

public class LruCache<K,V> implements Cache<K,V> {

    private final int capacity;
    private final DoublyLinkedList<K,V> dll;
    private final ConcurrentHashMap<K, Node<K,V>> map;

    public LruCache(int capacity) {
        this.capacity = capacity;
        this.dll = new DoublyLinkedList();
        this.map=new ConcurrentHashMap<>(capacity);

    }


    @Override
    public V get(K key) {
        System.out.println(map.toString());
        return getValueFromKey(key);
    }

    public synchronized V getValueFromKey(K key) {
        Node<K,V> val = map.get(key);
        dll.addToFront(val);
        return val.value;
    }

    @Override
    public void put(K key, V value) {
        putKeyValue(key, value);
    }

    public synchronized void putKeyValue(K key, V value) {

        if(map.containsKey(key)) {
            Node<K,V> val = map.get(key);
            val.value = value;
            dll.addToFront(val);
            return;
        }else if(map.size()==capacity) {
            Node<K,V> last = dll.removeLast();
            map.remove(last.key);
        }

            Node<K, V> insert = new Node<>(key, value);
            dll.addNodeDLL(insert);
            map.put(key, insert);

        }
}
