package day2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LevelValidator {
    private List<List<Integer>> levels;

    public LevelValidator(List<List<Integer>> levels) {
        this.levels = levels;
    }

    /**
     * Count how many safe levels are there in the list.
     * 
     * A level is safe if its items are:
     * 
     * <ul>
     * <li>
     * The items are either all increasing or all decreasing.
     * </li>
     * <li>
     * Any two adjacent levels differ by at least one and at most three.
     * </li>
     * </ul>
     * 
     * @return The number of safe levels
     */
    public int getSafeCount() {
        return this.getSafeLevels().size();
    }

    public List<List<Integer>> getSafeLevels() {
        Iterator<List<Integer>> itr = this.levels.iterator();
        List<List<Integer>> safeLevels = new ArrayList<>();

        while (itr.hasNext()) {
            List<Integer> level = itr.next();

            if (canFixOrIsSafe(level)) {
                safeLevels.add(level);
                continue;
            }
        }

        return safeLevels;
    }

    private static boolean isSafe(List<Integer> line) {
        Integer first = line.get(0);
        Integer second = line.get(1);

        if (second > first) {
            for (int i = 1; i < line.size(); i += 1) {
                Integer prev = line.get(i - 1);
                Integer cur = line.get(i);

                Integer diff = cur - prev;
                if (diff <= 0) {
                    return false;
                }

                if (diff > 3) {
                    return false;
                }
            }

            return true;
        } else {
            for (int i = 1; i < line.size(); i += 1) {
                Integer prev = line.get(i - 1);
                Integer cur = line.get(i);

                Integer diff = prev - cur;
                if (diff <= 0) {
                    return false;
                }

                if (diff > 3) {
                    return false;
                }
            }

            return true;
        }
    }

    /**
     * Check if there's any single level that we can fix to ensure the line is valid
     * 
     * @param line
     * @return Can it be fix
     */
    public static boolean canFixOrIsSafe(List<Integer> line) {
        if (isSafe(line)) {
            return true;
        }

        for (int i = 0; i < line.size(); i += 1) {
            List<Integer> removed = new ArrayList<>(line);
            removed.remove(i);

            if (isSafe(removed)) {
                return true;
            }
        }

        return false;
    }

}
