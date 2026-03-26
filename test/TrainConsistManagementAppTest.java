import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;

class TrainConsistManagementAppTest {

    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    private List<Bogie> getBogies() {
        return Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56),
                new Bogie("First Class", 24),
                new Bogie("Sleeper", 70)
        );
    }

    // 1. Total seat calculation
    @Test
    void testReduce_TotalSeatCalculation() {
        int total = getBogies().stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(222, total);
    }

    // 2. Multiple bogies aggregation
    @Test
    void testReduce_MultipleBogiesAggregation() {
        int total = getBogies().stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertTrue(total > 0);
    }

    // 3. Single bogie
    @Test
    void testReduce_SingleBogieCapacity() {
        List<Bogie> list = Arrays.asList(new Bogie("Sleeper", 72));

        int total = list.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(72, total);
    }

    // 4. Empty list
    @Test
    void testReduce_EmptyBogieList() {
        List<Bogie> empty = new ArrayList<>();

        int total = empty.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(0, total);
    }

    // 5. Correct capacity extraction
    @Test
    void testReduce_CorrectCapacityExtraction() {
        List<Integer> capacities = getBogies().stream()
                .map(b -> b.capacity)
                .collect(Collectors.toList());

        assertTrue(capacities.contains(72));
        assertTrue(capacities.contains(56));
        assertTrue(capacities.contains(24));
        assertTrue(capacities.contains(70));
    }

    // 6. All bogies included
    @Test
    void testReduce_AllBogiesIncluded() {
        int total = getBogies().stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        int manualSum = 72 + 56 + 24 + 70;

        assertEquals(manualSum, total);
    }

    // 7. Original list unchanged
    @Test
    void testReduce_OriginalListUnchanged() {
        List<Bogie> original = new ArrayList<>(getBogies());

        original.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(4, original.size());
    }
}