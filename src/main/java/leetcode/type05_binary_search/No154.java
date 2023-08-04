package leetcode.type05_binary_search;

import static leetcode.common.FormatConversion.strToIntArray;

/**
 * 154. Find Minimum in Rotated Sorted Array II
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-08-04 15:38
 */
public class No154 {

    public static void main(String[] args) {
        // 1
        System.out.println(findMin(strToIntArray("[1,3,5]")));
        // 0
        System.out.println(findMin(strToIntArray("[2,2,2,0,1]")));
    }

    /**
     * 思路：首先，如果尾元素大于头元素，则返回头；
     * 如果左中右都相等，不能判断最小值在哪边，需要两边同时递归，除此之外，如果中间大于等于左边，则查找右边，如果中间小于等于右边，则查找左边
     * 复杂度：log(n)
     */
    public static int findMin(int[] nums) {
        if (nums[0] < nums[nums.length - 1]) {
            return nums[0];
        }
        return doFinMin(nums, 0, nums.length - 1);
    }

    private static int doFinMin(int[] nums, int left, int right) {
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (nums[mid] == nums[left] && nums[mid] == nums[right]) {
                return Math.min(doFinMin(nums, left, mid), doFinMin(nums, mid, right));
            }
            if (nums[mid] >= nums[left]) {
                left = mid;
            }
            if (nums[mid] <= nums[right]) {
                right = mid;
            }
        }
        return nums[right];
    }

}