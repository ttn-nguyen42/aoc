/*
 * This source file was generated by the Gradle 'init' task
 */
package day2;

import day2.Reader.Result;

public class App {
    public static void main(String[] args) {
        if (args.length > 1) {
            System.err.println("Too many arguments provided!");
            return;
        }
        if (args.length == 0) {
            System.err.println("Missing path as argument");
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

        LevelValidator val = new LevelValidator(result.getLines());

        System.out.println(val.getSafeCount());
    }
}
