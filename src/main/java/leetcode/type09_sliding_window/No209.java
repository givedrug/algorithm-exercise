package leetcode.type09_sliding_window;

import static leetcode.common.FormatConversion.strToIntArray;

/**
 * 209. Minimum Size Subarray Sum
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-13 11:22
 */
public class No209 {

    public static void main(String[] args) {
        // 2
        System.out.println(minSubArrayLen(7, strToIntArray("[2,3,1,2,4,3]")));
        // 1
        System.out.println(minSubArrayLen(4, strToIntArray("[1,4,4]")));
        // 0
        System.out.println(minSubArrayLen(11, strToIntArray("[1,1,1,1,1,1,1,1]")));
    }

    /**
     * 思路：滑动窗口
     * 复杂度：n
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int l = 0, r = 1, min = nums.length, sum = nums[0];
        boolean found = false;
        while (l < nums.length && r <= nums.length) {
            // 如果和不够，扩大右边
            if (sum < target) {
                if (r == nums.length) {
                    break;
                }
                sum += nums[r];
                r++;
            } else {
                found = true;
                // 如果和够大，需要持续缩小左边，直到刚好大于或等于target
                if (sum - nums[l] < target) {
                    int len = r - l;
                    if (len < min) {
                        min = len;
                    }
                    if (len == 1) {
                        break;
                    }
                }
                sum -= nums[l];
                l++;
            }
        }
        return found ? min : 0;
    }

}