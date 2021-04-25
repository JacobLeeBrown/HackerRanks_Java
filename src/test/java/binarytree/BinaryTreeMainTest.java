package binarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeMainTest {

    private BinaryTreeMain target;

    @Test
    public void testEmptyTree() {
        target = new BinaryTreeMain();

        assertEquals(0, target.getNodes());
        assertEquals(0, target.getMean());
        assertEquals(0, target.getDepth());
    }

    @Test
    public void testAddInitialElem() {
        target = new BinaryTreeMain();

        boolean treeChanged = target.addElem(5);

        assertTrue(treeChanged);
        assertEquals(1, target.getNodes());
        assertEquals(5, target.getMean());
        assertEquals(1, target.getDepth());
    }

    @Test
    public void testAddSeveralElems() {
        target = new BinaryTreeMain();

        boolean treeChanged1 = target.addElem(5);
        boolean treeChanged2 = target.addElem(2);
        boolean treeChanged3 = target.addElem(8);
        boolean treeChanged4 = target.addElem(1);
        boolean treeChanged5 = target.addElem(3);
        boolean treeChanged6 = target.addElem(7);
        boolean treeChanged7 = target.addElem(9);

        assertTrue(treeChanged1 && treeChanged2 && treeChanged3 && treeChanged4 && treeChanged5 && treeChanged6 && treeChanged7);

        assertEquals(7, target.getNodes());

        float expectedMean = ((float) (1 + 2 + 3 + 5 + 7 + 8 + 9)) / 7f;
        assertEquals(expectedMean, target.getMean());

        assertEquals(3, target.getDepth());
    }

    @Test
    public void testTryingToAddElemAlreadyInTreeDoesNothing() {
        target = new BinaryTreeMain();

        boolean treeChanged1 = target.addElem(5);
        boolean treeChanged2 = target.addElem(2);
        boolean treeChanged3 = target.addElem(8);

        assertTrue(treeChanged1 && treeChanged2 && treeChanged3);
        assertEquals(3, target.getNodes());
        assertEquals(5.0f, target.getMean());
        assertEquals(2, target.getDepth());

        // Try to re-add element that already exists
        boolean treeChanged4 = target.addElem(2);

        assertFalse(treeChanged4);
        assertEquals(3, target.getNodes());
        assertEquals(5.0f, target.getMean());
        assertEquals(2, target.getDepth());
    }

}