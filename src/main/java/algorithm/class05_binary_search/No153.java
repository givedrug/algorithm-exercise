package algorithm.class05_binary_search;

import static algorithm.common.FormatConversion.strToIntArray;

/**
 * 153. Find Minimum in Rotated Sorted Array
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-14 00:01
 */
public class No153 {

    public static void main(String[] args) {
        // 1
        System.out.println(findMin(strToIntArray("[3,4,5,1,2]")));
        // 0
        System.out.println(findMin(strToIntArray("[4,5,6,7,0,1,2,3]")));
        // 11
        System.out.println(findMin(strToIntArray("[11,13,15,17]")));
    }

    /**
     * 思路：如果最左小于最右，说明没有经过翻转，直接返回第一个元素；其他情况通过二分查找找到突变处，返回右侧值即可
     * 复杂度：log(n)
     */
    public static int findMin(int[] nums) {
        if (nums[0] <= nums[nums.length - 1]) {
            return nums[0];
        }
        int left = 0, right = nums.length - 1;
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[left]) {
                left = mid;
            }
            if (nums[mid] < nums[right]) {
                right = mid;
            }
        }
        return nums[right];
    }

}