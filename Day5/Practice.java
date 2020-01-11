import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Practice
 */
public class Practice {

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>(Collections.reverseOrder());
        map.put(7, 9);
        map.put(5, 11);
        map.put(3, 12);
        sortedMap.putAll(map);
        int key = (int) sortedMap.keySet().toArray()[0];
        int value = sortedMap.get(key);
        System.out.println(key);
    }
}