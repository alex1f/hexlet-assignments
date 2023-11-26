package exercise;

import java.util.*;

// BEGIN
public class App {
    public static boolean scrabble(String input, String target){
        if (input.length() < target.length()){
            return false;
        }

        Map<Character, Integer> frequencies = findLetterFrequency(input);
        for (Character c : target.toCharArray()){
            c = Character.toLowerCase(c);
            if (frequencies.containsKey(c)){
                Integer currentCount = frequencies.get(c);
                currentCount--;
                if (currentCount > 0){
                    frequencies.put(c, currentCount);
                } else {
                    frequencies.remove(c);
                }
            } else {
                return false;
            }
        }

        return true;
    }

    private static Map<Character, Integer> findLetterFrequency(String input){
        Map<Character, Integer> frequency = new HashMap<>();
        for (Character c : input.toCharArray()){

            if (frequency.containsKey(c)){
                Integer currentCount = frequency.get(c);
                currentCount++;
                frequency.put(c, currentCount);
            } else {
                frequency.put(c, 1);
            }
        }
        return frequency;
    }
}
//END
