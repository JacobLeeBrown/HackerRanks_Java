package isthereapath;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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

        boolean result = isThereAPath(maze);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    /*
     * Complete the 'isThereAPath' function below.
     *
     * The function is expected to return a BOOLEAN.
     * The function accepts STRING_ARRAY maze as parameter.
     */

    public static boolean isThereAPath(List<String> maze) {
        if (maze.isEmpty()) {
            return false;
        }

        int rows = maze.size();
        int cols = maze.get(0).length();

        int[][] mazeArray = new int[rows][cols];
        for (int outer = 0; outer < rows; outer++) {
            for (int inner = 0; inner < cols; inner++) {
                mazeArray[outer][inner] = Integer.parseInt(String.valueOf(maze.get(outer).charAt(inner)));
            }
        }

        // If "first" or "last" space is 0, then we can't have a path
        if ((mazeArray[0][0] == 0) || (mazeArray[rows - 1][cols - 1] == 0)) {
            return false;
        }

        boolean[][] visited = new boolean[rows][cols];
        for (boolean[] row : visited) {
            Arrays.fill(row, false);
        }

        // Since we know we're starting in the top left corner of the maze, parentDir = 0 or 3 is fine.
        return isThereAPath(mazeArray, visited, rows, cols, 0, 0, 0);
    }


    /**
     * Recursively depth-first searches through mazeArray for a path of 1's to the last (bottom right) entry.
     *
     * @param mazeArray A 2D-array of ints, 1 being a "traversable" space, 0 being not-traversable.
     * @param visited   A 2D-array of booleans representing which spaces have already been searched.
     * @param rows      Total number of rows in the maze.
     * @param cols      Total number of columns in the maze.
     * @param curRow    The current row being searched (y-coordinate).
     * @param curCol    The current column being searched (x-coordinate).
     * @param parentDir Int representing which direction the parent call came from.
     *                  0 - up    (row-1)
     *                  1 - right (col+1)       - - 0 - -
     *                  2 - down  (row+1)       - 3 C 1 -
     *                  3 - left  (col-1)       - - 2 - -
     * @return False if current space cannot be traversed (path does not exist this way), True otherwise
     */
    private static boolean isThereAPath(int[][] mazeArray,
                                        boolean[][] visited,
                                        int rows,
                                        int cols,
                                        int curRow,
                                        int curCol,
                                        int parentDir) {
        if (visited[curRow][curCol]) { // Already visited space
            return false;
        }
        visited[curRow][curCol] = true;

        if (mazeArray[curRow][curCol] == 0) { // Haven't visited space, but it's not traversable
            return false;
        } else if ((curRow == (rows - 1)) && (curCol == (cols - 1))) { // End condition! We made it!
            return true;
        }

        // Check if path is up
        if (((curRow - 1) >= 0) && (parentDir != 0) &&
                isThereAPath(mazeArray, visited, rows, cols, curRow - 1, curCol, 2)) {
            return true;
        } // Check if path is right
        else if (((curCol + 1) < cols) && (parentDir != 1) &&
                isThereAPath(mazeArray, visited, rows, cols, curRow, curCol + 1, 3)) {
            return true;
        } // Check if path is down
        else if (((curRow + 1) < rows) && (parentDir != 2) &&
                isThereAPath(mazeArray, visited, rows, cols, curRow + 1, curCol, 0)) {
            return true;
        } // Check if path is left
        else {
            return ((curCol - 1) >= 0) && (parentDir != 3) &&
                    isThereAPath(mazeArray, visited, rows, cols, curRow, curCol - 1, 1);
        }
    }

}
