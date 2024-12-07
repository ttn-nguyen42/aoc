package day1;

import java.util.Iterator;
import java.util.List;

public class Similarity {
    private List<Integer> first;
    private BucketCounter<Integer> counter;

    public Similarity(List<Integer> first, List<Integer> second) {
        this.first = first;
        this.counter = this.count(second);
    }

    private BucketCounter<Integer> count(List<Integer> items) {
        BucketCounter<Integer> counter = new BucketCounter<>(items.size());
        Iterator<Integer> itr = items.iterator();

        while (itr.hasNext()) {
            Integer num = itr.next();

            counter.add(num);
        }

        return counter;
    }

    public int getSimilarity() {
        int score = 0;

        Iterator<Integer> itr = this.first.iterator();

        while (itr.hasNext()) {
            Integer num = itr.next();

            int occurances = this.counter.get(num);

            score += (num * occurances);
        }

        return score;
    }
}
