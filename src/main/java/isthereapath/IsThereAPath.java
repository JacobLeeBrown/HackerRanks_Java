package isthereapath;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class IsThereAPath {

    /*
     * Given a 2D-array where 1 indicates a walkable tile and 0 means not traversable, determine if there is a walkable
     * path from the top left space to the bottom right. You can move up, down, left, and right.
     *
     * Ex:
     *
     * 3
     * 1 0 0 0 1 0 0
     * 1 1 1 0 1 1 1
     * 1 0 1 1 1 0 1
     *
     * This does have a walkable path.
     *
     * Ex:
     *
     * 2
     * 1 1 1 1
     * 1 0 1 0
     * 1 0 0 1
     *
     * This does not have a walkable path.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int rowCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> maze = IntStream.range(0, rowCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .collect(toList());

        boolean result = Result.isThereAPath(maze);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    static class Result {

        /*
         * Complete the 'isThereAPath' function below.
         *
         * The function is expected to return a BOOLEAN.
         * The function accepts STRING_ARRAY maze as parameter.
         */

        public static boolean isThereAPath(List<String> maze) {
            return false;
        }

    }

}
