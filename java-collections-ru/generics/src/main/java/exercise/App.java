package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
public class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> parameters){
        List<Map<String, String>> output = new ArrayList<>(books);
        for (Map<String, String> book : books){
            for (Entry<String, String> parameter : parameters.entrySet()){
                String key = parameter.getKey();
                String value = parameter.getValue();

                if (!book.containsKey(key) || (book.containsKey(key) && !book.get(key).equals(value))){
                    output.remove(book);
                    break;
                }
            }
        }
        return output;
    }
}
//END
