package algorithm.class06_sorting;

import org.junit.Test;

import static algorithm.utils.FormatConversion.strToIntArray;
import static org.junit.Assert.assertArrayEquals;

/**
 * 75. Sort Colors
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2024-04-17 14:22
 */
public class No75 {

    @Test
    public void test1() {
        int[] nums = strToIntArray("[2,0,2,1,1,0]");
        sortColors(nums);
        assertArrayEquals(strToIntArray("[0,0,1,1,2,2]"), nums);
    }

    @Test
    public void test2() {
        int[] nums = strToIntArray("[2,0,1]");
        sortColors(nums);
        assertArrayEquals(strToIntArray("[0,1,2]"), nums);
    }

    /**
     * 思路：设置两个指针（类似于双轴快排）left表示0的右边界+1，right表示2的左边界-1
     * 遍历数组，如果当前元素是0，则交换left和i，如果当前元素是2，则交换right和i，i--，否则i++
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public void sortColors(int[] nums) {
        int left = 0, right = nums.length - 1;
        for (int i = 0; i <= right; i++) {
            if (nums[i] == 0) {
                swap(nums, i, left++);
            }
            if (nums[i] == 2) {
                // i需要减一，此位置需要重新遍历
                swap(nums, i--, right--);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

}