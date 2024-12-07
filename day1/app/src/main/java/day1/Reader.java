package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {
    private File f;

    public Reader(String path) {
        if (path.isEmpty()) {
            throw new IllegalArgumentException("input file path must not be empty");
        }

        f = new File(path);

    }

    public Result getItems() throws FileNotFoundException, IllegalArgumentException {
        try (Scanner s = new Scanner(this.f)) {
            Result r = new Result();

            int i = 0;
            while (s.hasNextInt()) {
                Integer nextInt = s.nextInt();
                if ((i % 2) == 0) {
                    r.addFirst(nextInt);
                } else {
                    r.addSecond(nextInt);
                }

                i += 1;
            }

            r.count = i % 2;

            return r;
        }

    }

    public class Result {
        private List<Integer> first;
        private List<Integer> second;
        private int count;

        public Result() {
            this.first = new ArrayList<>();
            this.second = new ArrayList<>();
            this.count = 0;
        }

        public List<Integer> getFirst() {
            return this.first;
        }

        public List<Integer> getSecond() {
            return this.second;
        }

        public int getCount() {
            return this.count;
        }

        protected void addFirst(Integer i) {
            this.first.add(i);
        }

        protected void addSecond(Integer i) {
            this.second.add(i);
        }
    }

}
