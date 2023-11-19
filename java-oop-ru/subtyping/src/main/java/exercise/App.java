package exercise;

import java.util.HashMap;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage data) {
        KeyValueStorage alternative = new InMemoryKV(data.toMap());
        data.toMap().entrySet().stream().forEach(entry -> {
            alternative.unset(entry.getKey());
            alternative.set(entry.getValue(), entry.getKey());
        });
        data.toMap().clear();
        alternative.toMap().entrySet().forEach(entry -> {
            data.set(entry.getKey(), entry.getValue());
        });
    }
}
// END
