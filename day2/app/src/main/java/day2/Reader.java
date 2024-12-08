package day2;

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
            while (s.hasNextLine()) {
                r.addLine(parseLine(s.nextLine()));
                i += 1;
            }

            r.total = i;
            return r;
        }

    }

    private List<Integer> parseLine(String l) {
        String[] itemsStr = l.split(" ");
        List<Integer> items = new ArrayList<>();

        for (String i : itemsStr) {
            Integer num = Integer.parseInt(i);
            items.add(num);
        }

        return items;
    }

    public class Result {
        private List<List<Integer>> lines;
        private int total;

        public Result() {
            this.lines = new ArrayList<>();
            this.total = 0;
        }

        public List<List<Integer>> getLines() {
            return this.lines;
        }

        public int getTotal() {
            return this.total;
        }

        protected void addLine(List<Integer> i) {
            this.lines.add(i);
        }
    }
}
