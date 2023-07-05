package leetcode.type01_array;

import static leetcode.common.FormatConversion.strToIntArray;

/**
 * 1991. Find the Middle Index in Array
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-03 19:28
 */
public class No1991 {
    public static void main(String[] args) {
        // 3
        System.out.println(findMiddleIndex(strToIntArray("[2,3,-1,8,4]")));
        // 2
        System.out.println(findMiddleIndex(strToIntArray("[1,-1,4]")));
        // -1
        System.out.println(findMiddleIndex(strToIntArray("[2,5]")));
    }

    /**
     * 思路：先求和，然后遍历数组，同时记录左右数组和
     * 复杂度：2n
     */
    public static int findMiddleIndex(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int left = 0;
        int right = sum - nums[0];
        for (int j = 0; j < nums.length; j++) {
            if (left == right) {
                return j;
            }
            if (j >= nums.length - 1) {
                break;
            }
            left += nums[j];
            right -= nums[j + 1];
        }
        return -1;
    }

}
