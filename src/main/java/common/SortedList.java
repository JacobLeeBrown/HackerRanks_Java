package common;

import java.util.ArrayList;
import java.util.function.BiFunction;

public class SortedList<T> {

    // Given A and B, return -1 if A < B, 0 if A == B, or 1 if A > B
    BiFunction<T, T, Integer> compareFunc;
    ArrayList<T> elems = new ArrayList<>();

    public SortedList(BiFunction<T, T, Integer> compareFunc) {
        this.compareFunc = compareFunc;
    }

    public SortedList(BiFunction<T, T, Integer> compareFunc, ArrayList<T> elems) {
        this.compareFunc = compareFunc;
        this.elems = elems;
    }

    public void addElem(T elem) {
        addElem(elem, 0, elems.size());
    }

    @SafeVarargs
    public final void addElems(T... elems) {
        for (T elem : elems) {
            this.addElem(elem);
        }
    }

    private void addElem(T elem, int startIdx, int endIdx) {
        if (startIdx == endIdx) {
            elems.add(startIdx, elem);
        }
        else {
            int midIdx = startIdx + (endIdx - startIdx) / 2;
            T mid = elems.get(midIdx);
            if (compareFunc.apply(elem, mid) == 0) {
                elems.add(midIdx, elem);
            }
            else if (compareFunc.apply(elem, mid) < 0) {
                addElem(elem, startIdx, midIdx);
            }
            else {
                addElem(elem, midIdx + 1, endIdx);
            }
        }
    }

    @SafeVarargs
    public final boolean isEqualTo(T... other) {
        if (elems.size() != other.length) {
            return false;
        }
        for (int i = 0; i < elems.size(); i++) {
            if (compareFunc.apply(elems.get(i), other[i]) != 0) {
                return false;
            }
        }
        return true;
    }

}
