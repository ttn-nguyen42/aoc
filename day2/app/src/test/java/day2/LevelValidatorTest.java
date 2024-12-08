/*
 * This source file was generated by the Gradle 'init' task
 */
package day2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LevelValidatorTest {
    public LevelValidatorTest() {

    }

    /**
     * A level can have decreasing items with difference between [1, 3]
     * 
     * @since All decreasing by 1 or 2
     */
    @Test
    public void successDecreasing() {
        List<List<Integer>> lines = new ArrayList<>();
        lines.add(Arrays.asList(7, 6, 4, 2, 1));

        LevelValidator val = new LevelValidator(lines);

        assertEquals(1, val.getSafeCount());
    }

    /**
     * A level cannot have 2 adjacent items with difference greater than 3 or less
     * than 1.
     * 
     * @since 7 - 2 > 3
     */
    @Test
    public void failDifferenceTooLarge() {
        List<List<Integer>> lines = new ArrayList<>();
        lines.add(Arrays.asList(1, 2, 7, 8, 9));

        LevelValidator val = new LevelValidator(lines);

        assertEquals(0, val.getSafeCount());
    }

    /**
     * A level cannot have both increasing and decreasing adjacent items.
     * 
     * But it should be valid because we can remove 2nd level to make it valid (Part
     * 2).
     * 
     * @since 3 decreases to 2 but 4 increases to 5
     */
    @Test
    public void failBothIncreasingAndDecreasing() {
        List<List<Integer>> lines = new ArrayList<>();
        lines.add(Arrays.asList(1, 3, 2, 4, 5));

        LevelValidator val = new LevelValidator(lines);

        assertEquals(1, val.getSafeCount());
    }

    /**
     * A level can have all items increasing with difference between [1, 3]
     * 
     * @since Level are all increasing by 1, 2 or 3
     */
    @Test
    public void successIncreasing() {
        List<List<Integer>> lines = new ArrayList<>();
        lines.add(Arrays.asList(1, 3, 6, 7, 9));

        LevelValidator val = new LevelValidator(lines);

        assertEquals(1, val.getSafeCount());
    }

    @Test
    public void testCase01() {
        List<List<Integer>> lines = new ArrayList<>();
        lines.add(Arrays.asList(7, 6, 4, 2, 1));
        lines.add(Arrays.asList(1, 2, 7, 8, 9));
        lines.add(Arrays.asList(9, 7, 6, 2, 1));
        lines.add(Arrays.asList(1, 3, 2, 4, 5));
        lines.add(Arrays.asList(8, 6, 4, 4, 1));
        lines.add(Arrays.asList(1, 3, 6, 7, 9));

        LevelValidator val = new LevelValidator(lines);

        assertEquals(4, val.getSafeCount());
    }

    /**
     * @since Is valid already
     */
    @Test
    public void canSafeTestCase01() {
        List<Integer> line = Arrays.asList(7, 6, 4, 2, 1);

        Boolean result = LevelValidator.canFixOrIsSafe(line);

        assertTrue(result);
    }

    /**
     * @since Can remove 2nd level (2) to make it safe
     */
    @Test
    public void canSafeTestCase02() {
        List<Integer> line = Arrays.asList(1, 3, 2, 4, 5);

        Boolean result = LevelValidator.canFixOrIsSafe(line);

        assertTrue(result);
    }

    /**
     * @since Can remove 3rd level (4) to make it safe
     */
    @Test
    public void canSafeTestCase03() {
        List<Integer> line = Arrays.asList(8, 6, 4, 4, 1);

        Boolean result = LevelValidator.canFixOrIsSafe(line);

        assertTrue(result);
    }

    /**
     * @since Cannot remove any 1 level to make it valid
     */
    @Test
    public void cannotSafeTestCase01() {
        List<Integer> line = Arrays.asList(1, 2, 7, 8, 9);

        Boolean result = LevelValidator.canFixOrIsSafe(line);

        assertFalse(result);
    }

    /**
     * @since Cannot remove any 1 level to make it valid
     */
    @Test
    public void cannotSafeTestCase02() {
        List<Integer> line = Arrays.asList(9, 7, 6, 2, 1);

        Boolean result = LevelValidator.canFixOrIsSafe(line);

        assertFalse(result);
    }

    /**
     * @since Cannot remove any 1 level to make it valid
     */
    @Test
    public void cannotSafeTestCase03() {
        List<Integer> line = Arrays.asList(64, 70, 71, 74, 75, 77, 84, 84);

        Boolean result = LevelValidator.canFixOrIsSafe(line);

        assertFalse(result);
    }
}
