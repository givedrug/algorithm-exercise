package algorithm.class06_sorting;

import java.util.Arrays;

import static algorithm.utils.FormatConversion.strToIntArray;

/**
 * 283. Move Zeroes
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-10-10 11:04
 */
public class No283 {

    public static void main(String[] args) {
        // [1,3,12,0,0]
        int[] nums0 = strToIntArray("[0,1,0,3,12]");
        moveZeroes(nums0);
        System.out.println(Arrays.toString(nums0));

        // [0]
        int[] nums1 = strToIntArray("[0]");
        moveZeroes(nums1);
        System.out.println(Arrays.toString(nums1));
    }

    /**
     * 思路：
     * 方法一：冒泡方式，复杂度O(n^2)
     * 方法二：双指针，左右指针中间皆为0，复杂度O(n)
     * <p>
     * 复杂度：O(n)
     */
    public static void moveZeroes(int[] nums) {
        int boundary = nums.length;
        if (boundary <= 1) {
            return;
        }

        int left = -1;
        for (int i = 0; i < boundary; i++) {
            if (0 == nums[i]) {
                if (-1 == left) {
                    left = i;
                }
            } else {
                if (-1 != left) {
                    nums[left] = nums[i];
                    nums[i] = 0;
                    left++;
                }
            }
        }
    }

    public static void moveZeroes0(int[] nums) {
        if (nums.length <= 1) {
            return;
        }

        int boundary = nums.length;
        for (int i = 0; i < boundary; i++) {
            if (0 == nums[i]) {
                for (int j = i; j < boundary - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                nums[boundary - 1] = 0;
                boundary = boundary - 1;
            }
        }
    }

}