package binarytree;

import java.util.ArrayList;
import java.util.Objects;

public class BinaryTreeMain {

    /* Design a naive integer BinaryTree where:
     *  1. Each subtree tracks its mean value
     *  2. Method to insert an element (if not already in tree) and returns if tree changed
     *  3. Method to remove an element (if in tree) and returns if tree changes
     *  4. Methods to access any subtree and get the subtree's mean
     */

    private BinaryTree theTree;
    private ArrayList<Integer> elems = new ArrayList<>();

    public BinaryTreeMain() {}

    public boolean addElem(int n) {
        if (elems.isEmpty()) {
            theTree = new BinaryTree(n);
            elems.add(n);
            return true;
        }
        else if (elems.contains(n)) {
            return false; // Do nothing -> element is already in tree
        }

        elems.add(n);
        theTree.addElem(n);
        return true;
    }

    public boolean removeElem(int n) {
        if (elems.isEmpty()) {
            return false;
        }
        else if (!elems.contains(n)) {
            return false; // Do nothing -> element is not in tree
        }

        elems.remove(Integer.valueOf(n));
        boolean treeEmpty = theTree.removeElem(n);
        if (treeEmpty) theTree = null;
        return true;
    }

    public int getNodes() {
        if (elems.isEmpty()) return 0;
        return theTree.nodes;
    }

    public float getMean() {
        if (elems.isEmpty()) return 0;
        return theTree.getMean();
    }

    public float getDepth() {
        if (elems.isEmpty()) return 0;
        return theTree.getDepth();
    }

    public static class BinaryTree {

        public int root;
        public int sum;
        public int nodes;

        public BinaryTree leftChild;
        public BinaryTree rightChild;

        public BinaryTree(int n) {
            root = n;
            sum = n;
            nodes = 1;
        }

        public void addElem(int n) {
            if (n < root) {
                if (Objects.isNull(leftChild)) {
                    leftChild = new BinaryTree(n);
                }
                else {
                    leftChild.addElem(n);
                }
            }
            else { // n > root -> can't be equal due to outside checks
                if (Objects.isNull(rightChild)) {
                    rightChild = new BinaryTree(n);
                }
                else {
                    rightChild.addElem(n);
                }
            }
            nodes += 1;
            sum += n;
        }

        // Returns whether or not the tree is empty, indicating to the parent to clear it
        public boolean removeElem(int n) {
            if (n == root) {
                int newRoot = 0;
                boolean leftNull = Objects.isNull(leftChild);
                boolean rightNull = Objects.isNull(rightChild);
                if (leftNull && rightNull) {
                    return true;
                }
                else if (leftNull) {
                    newRoot = rightChild.root;
                    boolean isEmpty = rightChild.removeElem(newRoot);
                    if (isEmpty) rightChild = null;
                }
                else { // else right is or is not null, doesn't matter since we're being naive
                    newRoot = leftChild.root;
                    boolean isEmpty = leftChild.removeElem(newRoot);
                    if (isEmpty) leftChild = null;
                }
                root = newRoot;
            }
            else if (n < root) {
                boolean isEmpty = leftChild.removeElem(n);
                if (isEmpty) leftChild = null;
            }
            else { // n > root
                boolean isEmpty = rightChild.removeElem(n);
                if (isEmpty) rightChild = null;
            }
            nodes -= 1;
            sum -= n;
            return false;
        }

        public int getDepth() {
            boolean leftNull = Objects.isNull(leftChild);
            boolean rightNull = Objects.isNull(rightChild);
            if (leftNull && rightNull) {
                return 1;
            }
            int leftDepth = leftNull ? 0 : leftChild.getDepth();
            int rightDepth = rightNull ? 0 : rightChild.getDepth();

            return leftDepth > rightDepth ? 1 + leftDepth : 1 + rightDepth;
        }

        public float getMean() {
            return ((float) sum) / nodes;
        }

    }

}

