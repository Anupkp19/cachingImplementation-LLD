import java.util.HashMap;
import java.util.LinkedHashSet;

public class LfuFrequencyList<K,V> {

    private  int minFreq;
    private final HashMap<Integer, LinkedHashSet<LfuNode<K,V>>>map;


    public LfuFrequencyList() {
        this.minFreq = 1;
        this.map = new HashMap<>();
    }


    public synchronized void addNode(LfuNode<K,V> node){
        if(node==null) return;
        int freq = node.getFrequency();
        map.computeIfAbsent(freq,
                f-> new LinkedHashSet<>())
                .add(node);
        if(freq==1){
            minFreq=1;
        }
    }

    public synchronized void removeNode(LfuNode<K,V> node) {
        if(node==null) return;
        int freq = node.frequency;
        LinkedHashSet<LfuNode<K,V>> list = map.get(freq);
        if(list==null) return;

        list.remove(node);

        if(list.isEmpty()){
            map.remove(freq);

            if (freq == minFreq) {
                minFreq++;
            }
        }
    }

    public synchronized LfuNode<K,V> removeMinFreq() {
        LinkedHashSet<LfuNode<K,V>> bucket = map.get(minFreq);
        if(bucket==null || bucket.isEmpty()) return null;

        LfuNode<K,V> node = bucket.iterator().next();
        bucket.remove(node);

        if(bucket.isEmpty()){
            map.remove(minFreq);
            minFreq++;

        }
        return node;
    }



}
