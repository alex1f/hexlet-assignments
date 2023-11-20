package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage data) {
        KeyValueStorage alternative = new InMemoryKV(data.toMap());
        data.toMap().entrySet().stream().forEach(entry -> {
            alternative.unset(entry.getKey());
            alternative.set(entry.getValue(), entry.getKey());
        });

        data.toMap().clear();
        alternative.toMap().forEach(data::set);
    }

    public static void main(String[] args) {
        KeyValueStorage storage = new InMemoryKV(Map.of("key", "value", "key2", "value2"));
        App.swapKeyValue(storage);
        storage.get("key", "default"); // "default"
        storage.get("value", "default"); // "key"

        System.out.println(storage.toMap()); // => {value=key, value2=key2}

    }
}
// END
