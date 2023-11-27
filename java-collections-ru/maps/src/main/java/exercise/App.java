package exercise;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String input){
        if (input.isEmpty()){
            return Collections.emptyMap();
        }
        String[] words = input.split(" ");
        Map<String, Integer> frequencies = new HashMap<>();
        for (String word : words){
            if (frequencies.containsKey(word)){
                Integer current = frequencies.get(word);
                current++;
                frequencies.put(word, current);
            } else {
                frequencies.put(word, 1);
            }
        }
        return frequencies;
    }

    public static String toString(Map<String, Integer> frequencies){
        if (frequencies.isEmpty()){
            return "{}";
        }
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        for (Map.Entry<String, Integer> entry : frequencies.entrySet()){
            result.append("  ")
                    .append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\n");
        }
        result.append("}");
        return result.toString();
    }
}
//END
