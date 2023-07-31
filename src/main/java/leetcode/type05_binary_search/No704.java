package leetcode.type05_binary_search;

import static leetcode.common.FormatConversion.strToIntArray;

/**
 * 704. Binary Search
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-31 10:42
 */
public class No704 {

    public static void main(String[] args) {
        // 4
        System.out.println(search(strToIntArray("[-1,0,3,5,9,12]"), 9));
        // -1
        System.out.println(search(strToIntArray("[-1,0,3,5,9,12]"), 2));
    }

    /**
     * 思路：二分查找
     * 复杂度：log(n)
     */
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, middle;
        while (left <= right) {
            middle = (left + right) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            // 因为middle一定不是目标值，所以左右指针可以跳过middle，加一或减一
            if (nums[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }

}