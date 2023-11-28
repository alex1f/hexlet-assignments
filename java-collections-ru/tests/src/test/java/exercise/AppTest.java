package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {
    private List<Integer> numbers = new ArrayList<>();

    @BeforeEach
    public void preparation(){
        numbers.add(1);
        numbers.add(4);
        numbers.add(-1);
        numbers.add(102);
    }

    @Test
    void testTake_baseCase() {
        // BEGIN
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
        List<Integer> expected = Collections.emptyList();
        List<Integer> result = App.take(numbers, 0);
        assertEquals(expected, result);
    }

    @Test
    public void sourceListIsEmpty(){
        numbers = Collections.emptyList();
        List<Integer> expected = Collections.emptyList();
        List<Integer> result = App.take(numbers, 0);
        assertEquals(expected, result);

        result = App.take(numbers, 3);
        assertEquals(expected, result);
    }

    @Test
    public void sourceListOnlyHasOneElement(){
        numbers = List.of(100);
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
