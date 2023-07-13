package leetcode.type12_two_pointers;

import static leetcode.common.FormatConversion.strToIntArray;

/**
 * 26. Remove Duplicates from Sorted Array
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-14 00:19
 */
public class No26 {

    public static void main(String[] args) {
        // 2
        System.out.println(removeDuplicates(strToIntArray("[1,1,2]")));
        // 5
        System.out.println(removeDuplicates(strToIntArray("[0,0,1,1,1,2,2,3,3,4]")));
    }

    /**
     * 思路：快慢指针
     * 复杂度：n
     */
    public static int removeDuplicates(int[] nums) {
        int left = 0, right = 1;
        while (right < nums.length) {
            if (nums[right] > nums[left]) {
                nums[left++ + 1] = nums[right];
            }
            right++;
        }
        return left + 1;
    }

}