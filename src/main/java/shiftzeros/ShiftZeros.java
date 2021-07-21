package shiftzeros;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given an array of integers, return that array with all of the zeros in it pushed to the end.
Any non-zero elements should retain their relative order.

Examples:
[1, 0, 3, 0, 0, 2] -> [1, 3, 2, 0, 0, 0]

Goals for solutions:
O(1) space (in-place)
O(n) time

*/

public class ShiftZeros {

    public static void main (String[] args) {
        List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        shiftZeros(nums);
        System.out.println(nums.toString());
    }

    public static void shiftZeros(List<Integer> nums) {
        int i = 0;
        int end = nums.size();
        while (i < end) {
            if (nums.get(i) == 0) {
                nums.remove(i);
                nums.add(0);
                end--;
            }
            else {
                i++;
            }
        }
    }
}
