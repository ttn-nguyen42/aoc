package day1;

import day1.Reader.Result;

public class App {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Too many arguments provided!");
            return;
        }
        String inputPath = args[0];
        Reader r = new Reader(inputPath);

        Result result;
        try {
            result = r.getItems();
        } catch (Exception e) {
            System.err.printf("Invalid input file: %s\n", e.getMessage());
            return;
        }

        Heap<Integer> leftColumn = new MinHeap<>(result.getFirst());
        Heap<Integer> rightColumn = new MinHeap<>(result.getSecond());

        Distancer d = new Distancer(leftColumn, rightColumn);

        System.out.println(d.getDistance());
    }
}
