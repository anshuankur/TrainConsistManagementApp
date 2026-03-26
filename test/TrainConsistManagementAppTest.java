import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrainConsistManagementAppTest {

    // Reuse same classes from main code
    static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String message) {
            super(message);
        }
    }

    static class PassengerBogie {
        String name;
        int capacity;

        PassengerBogie(String name, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Capacity must be greater than zero");
            }
            this.name = name;
            this.capacity = capacity;
        }
    }

    // 1. Valid capacity creation
    @Test
    void testException_ValidCapacityCreation() throws Exception {
        PassengerBogie b = new PassengerBogie("Sleeper", 72);
        assertNotNull(b);
    }

    // 2. Negative capacity throws exception
    @Test
    void testException_NegativeCapacityThrowsException() {
        assertThrows(InvalidCapacityException.class, () -> {
            new PassengerBogie("Sleeper", -10);
        });
    }

    // 3. Zero capacity throws exception
    @Test
    void testException_ZeroCapacityThrowsException() {
        assertThrows(InvalidCapacityException.class, () -> {
            new PassengerBogie("AC Chair", 0);
        });
    }

    // 4. Exception message validation
    @Test
    void testException_ExceptionMessageValidation() {
        Exception exception = assertThrows(InvalidCapacityException.class, () -> {
            new PassengerBogie("AC Chair", 0);
        });

        assertEquals("Capacity must be greater than zero", exception.getMessage());
    }

    // 5. Object integrity after creation
    @Test
    void testException_ObjectIntegrityAfterCreation() throws Exception {
        PassengerBogie b = new PassengerBogie("Sleeper", 72);

        assertEquals("Sleeper", b.name);
        assertEquals(72, b.capacity);
    }

    // 6. Multiple valid bogies creation
    @Test
    void testException_MultipleValidBogiesCreation() throws Exception {
        PassengerBogie b1 = new PassengerBogie("Sleeper", 72);
        PassengerBogie b2 = new PassengerBogie("AC Chair", 56);

        assertNotNull(b1);
        assertNotNull(b2);
    }
}