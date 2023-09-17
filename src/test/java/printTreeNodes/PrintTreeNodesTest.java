package printTreeNodes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrintTreeNodesTest {

    @Test
    public void testHappyPath() {
        PrintTreeNodes.Node[] input = new PrintTreeNodes.Node[] {
                new PrintTreeNodes.Node(10, "var", 5),
                new PrintTreeNodes.Node(5, "/", 0), // pid 0 doesn't exist --> Root node
                new PrintTreeNodes.Node(23, "usr", 5),
                new PrintTreeNodes.Node(18, "logs", 10),
                new PrintTreeNodes.Node(13, "bin", 5),
                new PrintTreeNodes.Node(2, "root", 23)
        };

        String actual = PrintTreeNodes.printTree(input);
        assertEquals("- /\n" +
                        "-- var\n" +
                        "--- logs\n" +
                        "-- usr\n" +
                        "--- root\n" +
                        "-- bin\n"
        , actual);
    }

}