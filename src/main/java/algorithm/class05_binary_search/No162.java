package algorithm.class05_binary_search;

import static algorithm.utils.FormatConversion.strToIntArray;

/**
 * 162. Find Peak Element
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-08-01 11:37
 */
public class No162 {

    public static void main(String[] args) {
        // 1
        System.out.println(findPeakElement(strToIntArray("[3,4,3,2,1]")));
        // 2
        System.out.println(findPeakElement(strToIntArray("[1,2,3,1]")));
        // 1或5
        System.out.println(findPeakElement(strToIntArray("[1,2,1,3,5,6,4]")));
    }

    /**
     * 思路：初始状态如果0元素比1元素大或者length-1元素比length-2元素大，则直接返回结果，除此之外，对于右侧增减性，必然有0处为增，length-1处为减
     * 对于中间值，则分为三种情况，如果中间值符合峰值，直接返回，如果中间值右侧增减性为增，则右边必有峰值，如果中间值右侧增减性为减，则左边必有峰值
     * 如此反复，必能找到峰值，且任何情况下，左右边界的增减性都是相反的。
     * 复杂度：log(n)
     */
    public static int findPeakElement(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return -1;
        }
        if (length == 1) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[length - 1] > nums[length - 2]) {
            return length - 1;
        }
        int left = 0, right = length - 1, middle;
        while (left < right) {
            middle = left + (right - left) / 2;
            // 如果left和right相差2，则中间一定是峰值点
            if (right - left == 2) {
                return middle;
            }
            int pre = nums[middle - 1];
            int curr = nums[middle];
            int next = nums[middle + 1];
            if (curr > pre && curr > next) {
                return middle;
            }
            if (rightIncrease(nums, left) ^ rightIncrease(nums, middle)) {
                right = middle;
            } else {
                left = middle;
            }
        }
        return -1;
    }

    /**
     * 查看右侧是否为增
     *
     * @param nums
     * @param index
     * @return
     */
    private static boolean rightIncrease(int[] nums, int index) {
        if (index == nums.length - 1) {
            return false;
        }
        return nums[index + 1] - nums[index] > 0;
    }

}