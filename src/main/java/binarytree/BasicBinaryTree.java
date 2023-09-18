package binarytree;

import java.util.Objects;

public class BasicBinaryTree {

    public int root;

    public BasicBinaryTree leftChild;
    public BasicBinaryTree rightChild;

    public BasicBinaryTree(int n) {
        root = n;
    }

    public void addElem(int n) {
        if (n < root) {
            if (Objects.isNull(leftChild)) {
                leftChild = new BasicBinaryTree(n);
            }
            else {
                leftChild.addElem(n);
            }
        }
        else { // n > root -> can't be equal due to outside checks
            if (Objects.isNull(rightChild)) {
                rightChild = new BasicBinaryTree(n);
            }
            else {
                rightChild.addElem(n);
            }
        }
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

}