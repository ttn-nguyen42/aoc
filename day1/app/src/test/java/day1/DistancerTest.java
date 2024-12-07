package day1;

import org.junit.jupiter.api.Test;

import day1.Reader.Result;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

class DistancerTest {
    private File resourcesDir;

    public DistancerTest() {
        resourcesDir = new File("src/test/resources");
    }

    @Test
    void testCase01() {
        List<Integer> first = Arrays.asList(3, 4, 2, 1, 3, 3);
        List<Integer> second = Arrays.asList(4, 3, 5, 3, 9, 3);

        Heap<Integer> firstHeap = new MinHeap<>(first);
        Heap<Integer> secondHeap = new MinHeap<>(second);

        Distancer dist = new Distancer(firstHeap, secondHeap);

        assertEquals(11, dist.getDistance());
    }

    @Test
    void testCase02() {
        String inputPath = resourcesDir.getAbsolutePath() + "/input_01.txt";
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

        assertEquals(1938424, d.getDistance());
    }
}
