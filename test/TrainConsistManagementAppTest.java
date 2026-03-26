import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Pattern;

class TrainConsistManagementAppTest {

    String trainRegex = "TRN-\\d{4}";
    String cargoRegex = "PET-[A-Z]{2}";

    Pattern trainPattern = Pattern.compile(trainRegex);
    Pattern cargoPattern = Pattern.compile(cargoRegex);

    // 1. Valid Train ID
    @Test
    void testRegex_ValidTrainID() {
        assertTrue(trainPattern.matcher("TRN-1234").matches());
    }

    // 2. Invalid Train ID formats
    @Test
    void testRegex_InvalidTrainIDFormat() {
        assertFalse(trainPattern.matcher("TRAIN12").matches());
        assertFalse(trainPattern.matcher("TRN12A").matches());
        assertFalse(trainPattern.matcher("1234-TRN").matches());
    }

    // 3. Valid Cargo Code
    @Test
    void testRegex_ValidCargoCode() {
        assertTrue(cargoPattern.matcher("PET-AB").matches());
    }

    // 4. Invalid Cargo Code formats
    @Test
    void testRegex_InvalidCargoCodeFormat() {
        assertFalse(cargoPattern.matcher("PET-ab").matches());
        assertFalse(cargoPattern.matcher("PET123").matches());
        assertFalse(cargoPattern.matcher("AB-PET").matches());
    }

    // 5. Train ID digit length validation
    @Test
    void testRegex_TrainIDDigitLengthValidation() {
        assertFalse(trainPattern.matcher("TRN-123").matches());
        assertFalse(trainPattern.matcher("TRN-12345").matches());
    }

    // 6. Cargo uppercase validation
    @Test
    void testRegex_CargoCodeUppercaseValidation() {
        assertFalse(cargoPattern.matcher("PET-ab").matches());
        assertFalse(cargoPattern.matcher("PET-aB").matches());
    }

    // 7. Empty input handling
    @Test
    void testRegex_EmptyInputHandling() {
        assertFalse(trainPattern.matcher("").matches());
        assertFalse(cargoPattern.matcher("").matches());
    }

    // 8. Exact pattern match (no extra chars)
    @Test
    void testRegex_ExactPatternMatch() {
        assertFalse(trainPattern.matcher("TRN-1234X").matches());
        assertFalse(cargoPattern.matcher("PET-ABC").matches());
    }
}