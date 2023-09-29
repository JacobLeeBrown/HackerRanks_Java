package common;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SortedListTest {

    @Test
    public void testAddElem() {
        SortedList<Integer> target = new SortedList<>(Integer::compareTo);

        target.addElem(5);
        target.addElem(10);
        target.addElem(3);
        target.addElem(4);
        target.addElem(12);
        target.addElem(8);

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(8);
        expected.add(10);
        expected.add(12);

        assertEquals(expected, target.elems);
    }

}