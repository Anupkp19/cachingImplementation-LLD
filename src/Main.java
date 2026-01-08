//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InvalidCapacityException {
//        Cache cache = new FifoCache(3);
//        Cache cache = new LruCache(3);
        Cache cache = new LfuCache(3);
        cache.put(1,2);
        cache.put(2,3);
        cache.put(1,"some");
        cache.put(4,5);
        cache.put(6,7);
        System.out.println(cache.get(6));

        System.out.println(cache.get(1));


    }
}