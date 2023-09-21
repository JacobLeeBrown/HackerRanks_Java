package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

public class GenericBinaryTree<T> {

    public T root;
    // Given A and B, return -1 if A < B, 0 if A == B, or 1 if A > B
    BiFunction<T, T, Integer> compareFunc;

    public GenericBinaryTree<T> leftChild;
    public GenericBinaryTree<T> rightChild;

    public GenericBinaryTree(T n, BiFunction<T, T, Integer> compareFunc) {
        root = n;
        this.compareFunc = compareFunc;
    }

    // TODO: Does not support balancing
    public void addElem(T n) {
        if (compareFunc.apply(n, root) < 0) {
            if (Objects.isNull(leftChild)) {
                leftChild = new GenericBinaryTree<>(n, this.compareFunc);
            }
            else {
                leftChild.addElem(n);
            }
        }
        else { // n > root -> can't be equal due to outside checks
            if (Objects.isNull(rightChild)) {
                rightChild = new GenericBinaryTree<>(n, this.compareFunc);
            }
            else {
                rightChild.addElem(n);
            }
        }
    }

    // Returns whether current tree should be removed due to being empty
    // TODO: Does not support balancing
    public boolean removeElem(T n) {
        boolean leftNull = Objects.isNull(leftChild);
        boolean rightNull = Objects.isNull(rightChild);
        if (compareFunc.apply(n, root) == 0) {
            // This is the node to remove
            if (leftNull && rightNull) {
                // This node is a leaf node and should be removed
                return true;
            }
            else if (leftNull) {
                replaceWith(rightChild);
                return false;
            }
            else if (rightNull) {
                replaceWith(leftChild);
                return false;
            }
            else {
                // Both children are preset
                T newRoot = popGreatestChild(leftChild);
                if (compareFunc.apply(newRoot, leftChild.root) == 0) {
                    leftChild = null;
                }
                root = newRoot;
            }
        }
        else if (compareFunc.apply(n, root) < 0 && !leftNull) {
            boolean isEmpty = leftChild.removeElem(n);
            if (isEmpty) leftChild = null;
        }
        else if (compareFunc.apply(n, root) > 0 && !rightNull) {
            boolean isEmpty = rightChild.removeElem(n);
            if (isEmpty) rightChild = null;
        }
        return false;
    }

    private T popGreatestChild(GenericBinaryTree<T> tree) {
        T res;
        if (Objects.isNull(tree.rightChild)) {
            // Found right most node, make replacements (if needed) and return
            res = tree.root;
            if (Objects.nonNull(tree.leftChild))
                tree.replaceWith(tree.leftChild);
        }
        else {
            // Keep searching!
            res = tree.popGreatestChild(tree.rightChild);
            // If greatest child was right child, then pop that sucker
            if (tree.compareFunc.apply(res, tree.rightChild.root) == 0) {
                tree.rightChild = null;
            }
        }
        return res;
    }

    private void replaceWith(GenericBinaryTree<T> tree) {
        root = tree.root;
        leftChild = tree.leftChild;
        rightChild = tree.rightChild;
    }

    public int getDepth() {
        int leftDepth = Objects.isNull(leftChild) ? 0 : leftChild.getDepth();
        int rightDepth = Objects.isNull(rightChild) ? 0 : rightChild.getDepth();
        return leftDepth > rightDepth ? 1 + leftDepth : 1 + rightDepth;
    }

    public List<T> asList() {
        List<T> leftList = Objects.isNull(leftChild) ? new ArrayList<>() : leftChild.asList();
        List<T> rightList = Objects.isNull(rightChild) ? new ArrayList<>() : rightChild.asList();

        List<T> res = new ArrayList<>(leftList);
        res.add(root);
        res.addAll(rightList);

        return res;
    }

    public boolean isLeaf() {
        return Objects.isNull(leftChild) && Objects.isNull(rightChild);
    }

}