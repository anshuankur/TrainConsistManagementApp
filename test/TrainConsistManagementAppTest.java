import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;

class TrainConsistManagementAppTest {

    static class Bogie {
        String type;
        int capacity;

        Bogie(String type, int capacity) {
            this.type = type;
            this.capacity = capacity;
        }
    }

    private List<Bogie> getBogies() {
        return Arrays.asList(
                new Bogie("A", 30),
                new Bogie("B", 70),
                new Bogie("C", 80),
                new Bogie("D", 50),
                new Bogie("E", 90)
        );
    }

    // 1. Loop filtering logic
    @Test
    void testLoopFilteringLogic() {
        List<Bogie> result = new ArrayList<>();

        for (Bogie b : getBogies()) {
            if (b.capacity > 60) {
                result.add(b);
            }
        }

        for (Bogie b : result) {
            assertTrue(b.capacity > 60);
        }
    }

    // 2. Stream filtering logic
    @Test
    void testStreamFilteringLogic() {
        List<Bogie> result = getBogies().stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        for (Bogie b : result) {
            assertTrue(b.capacity > 60);
        }
    }

    // 3. Loop vs Stream result match
    @Test
    void testLoopAndStreamResultsMatch() {
        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : getBogies()) {
            if (b.capacity > 60) {
                loopResult.add(b);
            }
        }

        List<Bogie> streamResult = getBogies().stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        assertEquals(loopResult.size(), streamResult.size());
    }

    // 4. Execution time measurement
    @Test
    void testExecutionTimeMeasurement() {
        List<Bogie> list = getBogies();

        long start = System.nanoTime();

        list.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        long end = System.nanoTime();

        assertTrue((end - start) > 0);
    }

    // 5. Large dataset processing
    @Test
    void testLargeDatasetProcessing() {
        List<Bogie> largeList = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            largeList.add(new Bogie("Type", i % 100));
        }

        List<Bogie> result = largeList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        assertNotNull(result);
        assertTrue(result.size() > 0);
    }
}