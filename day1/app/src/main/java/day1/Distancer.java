package day1;

import java.util.Iterator;

public class Distancer {
    private Iterator<Integer> first;
    private Iterator<Integer> second;

    public Distancer(Iterator<Integer> first, Iterator<Integer> second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returns the distance between elements of the 2 sets of Integers.
     * 
     * @implNote The total distance is calculated while both of the set are not
     *           empty.
     * @return Total distance
     */
    public Integer getDistance() {
        int distance = 0;

        while (this.first.hasNext() && this.second.hasNext()) {
            Integer firstItem = this.first.next();
            Integer secondItem = this.second.next();

            int dist = 0;
            if (firstItem.compareTo(secondItem) >= 0) {
                dist = (firstItem - secondItem);
            } else {
                dist = (secondItem - firstItem);
            }
            // System.out.printf("%d - %d\n", firstItem, secondItem);

            distance += dist;
        }

        return distance;
    }
}