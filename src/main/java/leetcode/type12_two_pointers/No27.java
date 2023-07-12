package leetcode.type12_two_pointers;

import static leetcode.common.FormatConversion.strToIntArray;

/**
 * 27. Remove Element
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-12 11:09
 */
public class No27 {

    public static void main(String[] args) {
        // 3
        System.out.println(removeElement(strToIntArray("[1,3,5,6]"), 5));
        // 4
        System.out.println(removeElement(strToIntArray("[1,3,5,6]"), 2));
        // 2
        System.out.println(removeElement(strToIntArray("[3,2,2,3]"), 3));
        // 5
        System.out.println(removeElement(strToIntArray("[0,1,2,2,3,0,4,2]"), 2));
    }

    /**
     * 思路：快慢双指针
     * 复杂度：n
     */
    public static int removeElement(int[] nums, int val) {
        int l = 0, r = 0;
        while (l < nums.length && r < nums.length) {
            if (nums[r] != val) {
                if (l != r) {
                    nums[l] = nums[r];
                }
                r++;
                l++;
            } else {
                r++;
            }
        }
        return l;
    }

}