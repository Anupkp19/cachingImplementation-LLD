import java.security.Key;

public class LfuNode<K,V> {

    K key;
    V value;
    int frequency;

    LfuNode(K key, V value){
        this.key=key;
        this.value=value;
        this.frequency=1;
    }

    public K getKey(){
        return key;
    }

    public V getValue(){
        return value;
    }

    public int getFrequency(){
        return frequency;
    }

    public synchronized void increaseFreq(){
        this.frequency++;
    }
}
