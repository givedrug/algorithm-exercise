package leetcode.type01_array;

import static leetcode.common.FormatConversion.strToIntArray;

/**
 * 35. Search Insert Position
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-05 10:38
 */
public class No35 {
    public static void main(String[] args) {
        // 2
        System.out.println(searchInsert(strToIntArray("[1,3,5,6]"), 5));
        // 1
        System.out.println(searchInsert(strToIntArray("[1,3,5,6]"), 2));
        // 4
        System.out.println(searchInsert(strToIntArray("[1,3,5,6]"), 7));
    }

    /**
     * 思路：二分
     * 复杂度：log(n)
     */
    public static int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

}
