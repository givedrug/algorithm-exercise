package algorithm.class01_array;

import static algorithm.common.FormatConversion.strToIntArray;

/**
 * 485. Max Consecutive Ones
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-13 10:40
 */
public class No485 {

    public static void main(String[] args) {
        // 3
        System.out.println(findMaxConsecutiveOnes(strToIntArray("[1,1,0,1,1,1]")));
        // 2
        System.out.println(findMaxConsecutiveOnes(strToIntArray("[1,0,1,1,0,1]")));
    }

    /**
     * 思路：顺序遍历，记录连续1个数
     * 复杂度：n
     */
    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int current = 0;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == 1) {
                while (nums[i] == 1) {
                    current++;
                    i++;
                    if (i >= nums.length) {
                        break;
                    }
                }
                if (current > max) {
                    max = current;
                }
                current = 0;
            }
            i++;
        }
        return max;
    }

}