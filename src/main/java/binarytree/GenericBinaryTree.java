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

    // Returns whether or not the tree is empty, indicating to the parent to clear it
    public boolean removeElem(T n) {
        if (compareFunc.apply(n, root) == 0) {
            T newRoot;
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
        else if (compareFunc.apply(n, root) < 0) {
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

}