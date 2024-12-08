package day2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import day2.Reader.Result;

public class ReaderTest {
    public ReaderTest() {

    }

    @Test
    public void failIfFileNotExists() {
        String path = "/tmp/not_exists";

        Reader r = new Reader(path);

        assertThrows(FileNotFoundException.class, () -> {
            r.getItems();
        });
    }

    private void initialize(String path, List<String> lines) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line).append(System.lineSeparator()); // Add lines with proper line breaks
        }

        byte[] data = sb.toString().getBytes(StandardCharsets.UTF_8);
        this.initializeFile(path, data);
    }

    private void initializeFile(String path, byte[] data) throws IOException {
        Files.write(
                Paths.get(path),
                data,
                StandardOpenOption.CREATE);
    }

    private void clean(String path) throws IOException {
        Files.delete(Paths.get(path));
    }

    @Test
    public void successReturnsEmptyIfFileEmpty() {
        String tmpFile = "tmp_successReturnsEmptyIfFileEmpty";
        List<String> inputLines = Arrays.asList(
                "51 52 55 58 60 61 62 61",
                "64 65 67 70 72 74 77 77",
                "2 4 6 9 11 14 18");

        try {
            assertDoesNotThrow(() -> {
                this.initialize(tmpFile, inputLines);
            });

            List<List<Integer>> expected = Arrays.asList(
                    Arrays.asList(51, 52, 55, 58, 60, 61, 62, 61),
                    Arrays.asList(64, 65, 67, 70, 72, 74, 77, 77),
                    Arrays.asList(2, 4, 6, 9, 11, 14, 18));

            assertDoesNotThrow(() -> {
                Reader r = new Reader(tmpFile);
                Result res = r.getItems();

                assertEquals(expected, res.getLines());
            });
        } finally {
            assertDoesNotThrow(() -> {
                this.clean(tmpFile);
            });
        }

    }
}
