package day1;

import java.util.HashMap;
import java.util.Map;

public class BucketCounter<T> {
    private Map<T, Integer> appearances;

    public BucketCounter(int maxSize) {
        this.appearances = new HashMap<>(maxSize);
    }

    /**
     * Add an item to the counter.
     * 
     * @param item
     */
    public void add(T item) {
        int currCount = this.appearances.getOrDefault(item, 0);
        this.appearances.put(item, currCount + 1);
    }

    /**
     * Returns the number of apperances of an item
     * 
     * @param item
     * @return Number of appearances
     */
    public int get(T item) {
        return this.appearances.getOrDefault(item, 0);
    }
}
