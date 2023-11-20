package exercise;

import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage{
    public Map<String, String> data;

    public InMemoryKV(Map<String, String> data) {
        this.data = new TreeMap<>();
        this.data.putAll(data);
    }

    @Override
    public void set(String key, String value) {
        data.put(key, value);
    }

    @Override
    public void unset(String key) {
        data.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return data.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return data;
    }
}
// END
