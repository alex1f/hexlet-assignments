package exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;


import org.junit.jupiter.api.Test;

class AppTest {
    @Test
    void testTake_baseCase() {
        // BEGIN
        List<Integer> numbers = List.of(1, 4, -1, 102);
        List<Integer> expected = List.of(1, 4);
        List<Integer> result = App.take(numbers, 2);
        assertEquals(expected, result);

        expected = numbers;
        result = App.take(numbers, 4);
        assertEquals(expected, result);

        expected = List.of(1);
        result = App.take(numbers, 1);
        assertEquals(expected, result);
        // END
    }

    @Test
    public void takeZeroElements(){
        List<Integer> numbers = List.of(1, 4, -1, 102);
        List<Integer> expected = Collections.emptyList();
        List<Integer> result = App.take(numbers, 0);
        assertEquals(expected, result);
    }

    @Test
    public void sourceListIsEmpty(){
        List<Integer> numbers = Collections.emptyList();
        List<Integer> expected = Collections.emptyList();
        List<Integer> result = App.take(numbers, 0);
        assertEquals(expected, result);

        result = App.take(numbers, 3);
        assertEquals(expected, result);
    }

    @Test
    public void sourceListOnlyHasOneElement(){
        List<Integer> numbers = List.of(100);
        List<Integer> expected = numbers;
        List<Integer> result = App.take(numbers, 1);
        assertEquals(expected, result);

        result = App.take(numbers, 3);
        assertEquals(expected, result);

        expected = Collections.emptyList();
        result = App.take(numbers, 0);
        assertEquals(expected, result);

    }
}
