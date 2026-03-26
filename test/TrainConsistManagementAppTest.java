import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class TrainConsistManagementAppTest {

    static class GoodsBogie {
        String type;
        String cargo;

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }
    }

    // Helper method (same logic as UC12)
    private boolean isSafe(List<GoodsBogie> list) {
        return list.stream()
                .allMatch(g -> {
                    if (g.type.equals("Cylindrical")) {
                        return g.cargo.equals("Petroleum");
                    }
                    return true;
                });
    }

    // 1. All bogies valid
    @Test
    void testSafety_AllBogiesValid() {
        List<GoodsBogie> list = Arrays.asList(
                new GoodsBogie("Cylindrical", "Petroleum"),
                new GoodsBogie("Open", "Coal"),
                new GoodsBogie("Box", "Grain")
        );

        assertTrue(isSafe(list));
    }

    // 2. Cylindrical with invalid cargo
    @Test
    void testSafety_CylindricalWithInvalidCargo() {
        List<GoodsBogie> list = Arrays.asList(
                new GoodsBogie("Cylindrical", "Coal") // invalid
        );

        assertFalse(isSafe(list));
    }

    // 3. Non-cylindrical bogies allowed
    @Test
    void testSafety_NonCylindricalBogiesAllowed() {
        List<GoodsBogie> list = Arrays.asList(
                new GoodsBogie("Open", "Coal"),
                new GoodsBogie("Box", "Grain")
        );

        assertTrue(isSafe(list));
    }

    // 4. Mixed bogies with violation
    @Test
    void testSafety_MixedBogiesWithViolation() {
        List<GoodsBogie> list = Arrays.asList(
                new GoodsBogie("Cylindrical", "Petroleum"),
                new GoodsBogie("Cylindrical", "Coal") // invalid
        );

        assertFalse(isSafe(list));
    }

    // 5. Empty bogie list
    @Test
    void testSafety_EmptyBogieList() {
        List<GoodsBogie> list = new ArrayList<>();

        assertTrue(isSafe(list)); // allMatch returns true for empty list
    }
}