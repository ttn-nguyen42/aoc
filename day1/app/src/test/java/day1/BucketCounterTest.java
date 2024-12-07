package day1;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import org.junit.jupiter.api.Test;

import day1.Reader.Result;

public class BucketCounterTest {
    private File resourcesDir;

    public BucketCounterTest() {
        resourcesDir = new File("src/test/resources");
    }

    @Test
    void testCase01() {
        String inputPath = resourcesDir.getAbsolutePath() + "/input_01.txt";
        Reader r = new Reader(inputPath);

        Result result;
        try {
            result = r.getItems();
        } catch (Exception e) {
            fail(e.getMessage());
            return;
        }
        Similarity s = new Similarity(result.getFirst(), result.getSecond());

        System.out.println(s.getSimilarity());
    }
}
