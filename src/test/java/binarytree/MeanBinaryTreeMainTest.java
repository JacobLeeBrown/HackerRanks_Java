package binarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeanBinaryTreeMainTest {

    private MeanBinaryTreeMain target;

    @Test
    public void testEmptyTree() {
        target = new MeanBinaryTreeMain();

        assertEquals(0, target.getNodes());
        assertEquals(0, target.getMean());
        assertEquals(0, target.getDepth());
    }

    @Test
    public void testAddInitialElem() {
        target = new MeanBinaryTreeMain();

        boolean treeChanged = target.addElem(5);

        assertTrue(treeChanged);
        assertEquals(1, target.getNodes());
        assertEquals(5, target.getMean());
        assertEquals(1, target.getDepth());
    }

    @Test
    public void testAddSeveralElems() {
        target = new MeanBinaryTreeMain();

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
        target = new MeanBinaryTreeMain();

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

    @Test
    public void testRemoveElemFromEmptyTree() {
        target = new MeanBinaryTreeMain();

        boolean treeChanged = target.removeElem(5);

        assertFalse(treeChanged);
        assertEquals(0, target.getNodes());
        assertEquals(0, target.getMean());
        assertEquals(0, target.getDepth());
    }

    @Test
    public void testRemoveOnlyElemFromTree() {
        target = new MeanBinaryTreeMain();

        boolean treeChanged1 = target.addElem(5);
        boolean treeChanged2 = target.removeElem(5);

        assertTrue(treeChanged1 && treeChanged2);
        assertEquals(0, target.getNodes());
        assertEquals(0, target.getMean());
        assertEquals(0, target.getDepth());
    }

    @Test
    public void testRemoveElemThatIsNotInTreeDoesNothing() {
        target = new MeanBinaryTreeMain();

        target.addElem(5);
        boolean treeChanged = target.removeElem(2);

        assertFalse(treeChanged);
        assertEquals(1, target.getNodes());
        assertEquals(5, target.getMean());
        assertEquals(1, target.getDepth());
    }

    @Test
    public void testRemoveMultipleElems() {
        target = new MeanBinaryTreeMain();

        instantiateBasicTree(target);

        boolean treeChanged = target.removeElem(2);

        assertTrue(treeChanged);
        assertEquals(6, target.getNodes());
        assertEquals(((float)33)/6, target.getMean());
        assertEquals(3, target.getDepth());

        treeChanged = target.removeElem(3);

        assertTrue(treeChanged);
        assertEquals(5, target.getNodes());
        assertEquals(6, target.getMean());
        assertEquals(3, target.getDepth());

        target.removeElem(7);
        target.removeElem(9);

        assertEquals(3, target.getNodes());
        assertEquals(((float)14)/3, target.getMean());
        assertEquals(2, target.getDepth());
    }

    private void instantiateBasicTree(MeanBinaryTreeMain target) {
        target.addElem(5);
        target.addElem(2);
        target.addElem(8);
        target.addElem(1);
        target.addElem(3);
        target.addElem(7);
        target.addElem(9);
    }

}