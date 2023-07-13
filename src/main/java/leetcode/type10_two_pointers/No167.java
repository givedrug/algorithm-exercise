package leetcode.type10_two_pointers;

import java.util.Arrays;

import static leetcode.common.FormatConversion.strToIntArray;

/**
 * 167. Two Sum II - Input Array Is Sorted
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-11 17:53
 */
public class No167 {

    public static void main(String[] args) {
        // [1,2]
        System.out.println(Arrays.toString(twoSum(strToIntArray("[2,7,11,15]"), 9)));
        // [1,3]
        System.out.println(Arrays.toString(twoSum(strToIntArray("[2,3,4]"), 6)));
        // [1,2]
        System.out.println(Arrays.toString(twoSum(strToIntArray("[-1,0]"), -1)));
    }

    /**
     * 思路：对撞双指针
     * 复杂度：n
     */
    public static int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                return new int[]{l + 1, r + 1};
            }
            if (numbers[l] + numbers[r] > target) {
                r--;
            } else {
                l++;
            }
        }
        return new int[2];
    }

}