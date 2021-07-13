package isthereapath;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsThereAPathTest {

    @Test
    public void testIsThereAPathHappyPath() {
        List<String> maze = Arrays.asList(
                "10000",
                "11111",
                "00001"
        );

        assertTrue(IsThereAPath.isThereAPath(maze));
    }

    @Test
    public void testIsThereAPathWhenThereIsNoPathReturnFalse() {
        List<String> maze = Arrays.asList(
                "10000",
                "11011",
                "00001"
        );

        assertFalse(IsThereAPath.isThereAPath(maze));
    }

    @Test
    public void testIsThereAPathWhenMazeIsEmptyReturnFalse() {
        List<String> maze = Collections.emptyList();

        assertFalse(IsThereAPath.isThereAPath(maze));
    }

    @Test
    public void testIsThereAPathWhenThereAreMultiplePathsReturnTrue() {
        List<String> maze = Arrays.asList(
                "11111",
                "00101",
                "00111"
        );

        assertTrue(IsThereAPath.isThereAPath(maze));
    }

    @Test
    public void testIsThereAPathWhenMazeIsFullyTraversableReturnTrue() {
        List<String> maze = Arrays.asList(
                "11111",
                "11111",
                "11111"
        );

        assertTrue(IsThereAPath.isThereAPath(maze));
    }

    @Test
    public void testIsThereAPathWhenMazeIsSingleRow() {
        List<String> mazeWithPath = Collections.singletonList(
                "11111"
        );

        assertTrue(IsThereAPath.isThereAPath(mazeWithPath));

        List<String> mazeWithoutPath = Collections.singletonList(
                "10001"
        );

        assertFalse(IsThereAPath.isThereAPath(mazeWithoutPath));
    }

    @Test
    public void testIsThereAPathWhenMazeIsSingleCol() {
        List<String> mazeWithPath = Arrays.asList(
                "1",
                "1",
                "1",
                "1",
                "1"
        );

        assertTrue(IsThereAPath.isThereAPath(mazeWithPath));

        List<String> mazeWithoutPath = Arrays.asList(
                "1",
                "0",
                "1",
                "1",
                "1"
        );

        assertFalse(IsThereAPath.isThereAPath(mazeWithoutPath));
    }

    @Test
    public void testIsThereAPathWhenStartOrEndIsZeroReturnFalse() {
        // Start is 0
        List<String> maze = Arrays.asList(
                "01111",
                "11111",
                "11111"
        );
        assertFalse(IsThereAPath.isThereAPath(maze));

        // End is 0
        maze = Arrays.asList(
                "11111",
                "11111",
                "11110"
        );
        assertFalse(IsThereAPath.isThereAPath(maze));

        // Both are 0
        maze = Arrays.asList(
                "01111",
                "11111",
                "11110"
        );
        assertFalse(IsThereAPath.isThereAPath(maze));
    }

    @Test
    public void testIsThereAPathWhenCrazyMazeWithPathReturnsTrue() {
        List<String> maze = Arrays.asList(
                "101001110",
                "111111011",
                "010110010",
                "110011110",
                "011110100",
                "000010111"
        );

        assertTrue(IsThereAPath.isThereAPath(maze));
    }

}