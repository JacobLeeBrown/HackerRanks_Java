package binarytree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenericBinaryTreeTest {

    @Test
    public void testIntegerTree_addElem() {
        GenericBinaryTree<Integer> target = getIntegerBinaryTree();

        assertTrue(target.leftChild.leftChild.isLeaf());
        assertTrue(target.leftChild.rightChild.isLeaf());
        assertTrue(target.rightChild.leftChild.isLeaf());
        assertTrue(target.rightChild.rightChild.isLeaf());

        assertEquals(5, target.root);
        assertEquals(2, target.leftChild.root);
        assertEquals(8, target.rightChild.root);
        assertEquals(1, target.leftChild.leftChild.root);
        assertEquals(3, target.leftChild.rightChild.root);
        assertEquals(7, target.rightChild.leftChild.root);
        assertEquals(9, target.rightChild.rightChild.root);
    }

    @Test
    public void testIntegerTree_getDepth() {
        GenericBinaryTree<Integer> target = getIntegerBinaryTree();
        assertEquals(3, target.getDepth());

        target = new GenericBinaryTree<>(10, Integer::compareTo);
        assertEquals(1, target.getDepth());
    }

    @Test
    public void testIntegerTree_asList() {
        GenericBinaryTree<Integer> target = getIntegerBinaryTree();

        List<Integer> expectedAsList = new ArrayList<>();
        expectedAsList.add(1);
        expectedAsList.add(2);
        expectedAsList.add(3);
        expectedAsList.add(5);
        expectedAsList.add(7);
        expectedAsList.add(8);
        expectedAsList.add(9);

        assertEquals(expectedAsList, target.asList());
    }

    @Test
    public void testIntegerTree_removeElem() {
        GenericBinaryTree<Integer> target = getIntegerBinaryTree();

        target.removeElem(5);
        target.removeElem(8);
        target.removeElem(7);

        assertEquals(3, target.getDepth());

        List<Integer> expectedAsList = new ArrayList<>();
        expectedAsList.add(1);
        expectedAsList.add(2);
        expectedAsList.add(3);
        expectedAsList.add(9);

        assertEquals(expectedAsList, target.asList());

        // Try removing elems that don't exist
        target.removeElem(0);
        target.removeElem(5);
        target.removeElem(-6);

        assertEquals(expectedAsList, target.asList());
    }

    private GenericBinaryTree<Integer> getIntegerBinaryTree() {
        GenericBinaryTree<Integer> tree = new GenericBinaryTree<>(5, Integer::compareTo);
        tree.addElem(2);
        tree.addElem(8);
        tree.addElem(1);
        tree.addElem(3);
        tree.addElem(7);
        tree.addElem(9);
        return tree;
    }

    @Test
    public void testStringTree() {
        GenericBinaryTree<String> target = new GenericBinaryTree<>("hello", String::compareTo);

        target.addElem("never");
        target.addElem("gonna");
        target.addElem("give");
        target.addElem("you");
        target.addElem("up");
        target.addElem("111");

        assertEquals(4, target.getDepth());

        List<String> expectedAsList = new ArrayList<>();
        expectedAsList.add("111");
        expectedAsList.add("give");
        expectedAsList.add("gonna");
        expectedAsList.add("hello");
        expectedAsList.add("never");
        expectedAsList.add("up");
        expectedAsList.add("you");

        assertEquals(expectedAsList, target.asList());

        target.removeElem("give");
        target.removeElem("hello");

        expectedAsList = new ArrayList<>();
        expectedAsList.add("111");
        expectedAsList.add("gonna");
        expectedAsList.add("never");
        expectedAsList.add("up");
        expectedAsList.add("you");

        assertEquals(expectedAsList, target.asList());

        // Try removing elems that don't exist
        target.removeElem("foo");
        target.removeElem("bar");
        target.removeElem("boo");

        assertEquals(expectedAsList, target.asList());
    }

}