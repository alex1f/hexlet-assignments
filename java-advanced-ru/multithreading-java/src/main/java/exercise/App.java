package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        MaxThread maxThread = new MaxThread(numbers);
        maxThread.start();
        MinThread minThread = new MinThread(numbers);
        minThread.start();

        try {
            maxThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            minThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Map<String, Integer> results = new HashMap<>();
        results.put("min", minThread.getMinNumber());
        results.put("max", maxThread.getMaximum());

        return results;
    }

    public static void main(String[] args) {
        int[] numbers = {10, -4, 67, 100, -100, 8};
        System.out.println(App.getMinMax(numbers));
    }
    // END
}
