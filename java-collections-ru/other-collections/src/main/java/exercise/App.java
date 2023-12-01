package exercise;

import java.util.*;

// BEGIN
public class App {

    public static <V> LinkedHashMap<String, String> genDiff(Map<String, V> source, Map<String, V> other){
        Map<String, String> analysis = new TreeMap<>();
        for (Map.Entry<String, V> entry : source.entrySet()){
            String sourceKey = entry.getKey();
            V sourceValue = entry.getValue();

            if (other.containsKey(sourceKey)){
                if (other.get(sourceKey).equals(sourceValue)){
                    analysis.put(sourceKey, "unchanged");
                } else {
                    analysis.put(sourceKey, "changed");
                }
                
            } else {
                analysis.put(sourceKey, "deleted");
            }
        }

        other.keySet().removeAll(source.keySet());
        for (String key : other.keySet()){
            analysis.put(key, "added");
        }

        return new LinkedHashMap<>(analysis);
    }
}
//END
