package common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortedListTest {

    @Test
    public void testAddElem() {
        SortedList<Integer> target = new SortedList<>(Integer::compareTo);

        target.addElems(5, 10, 3, 4, 12, 8);

        assertTrue(target.isEqualTo(3, 4, 5, 8, 10, 12));
    }

    @Test
    public void testAddElem_duplicates() {
        SortedList<Integer> target = new SortedList<>(Integer::compareTo);

        target.addElems(5, 10, 3, 5, -1, 0, 0, 10, 7, 5);

        assertTrue(target.isEqualTo(-1, 0, 0, 3, 5, 5, 5, 7, 10, 10));
    }

}