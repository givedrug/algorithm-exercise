package algorithm.class06_sorting;

import java.util.Arrays;
import java.util.Comparator;

import static algorithm.utils.FormatConversion.strToIntArray;

/**
 * 215. Kth Largest Element in an Array
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-10-11 20:09
 */
public class No215 {

    public static void main(String[] args) {
        // 5
        System.out.println(findKthLargest(strToIntArray("[3,2,1,5,6,4]"), 2));

        // 4
        System.out.println(findKthLargest(strToIntArray("[3,2,3,1,2,4,5,5,6]"), 4));
    }

    /**
     * 思路：
     * 方法一：先排序，然后返回第k个元素，时间复杂度为n*log(n)
     * 方法二：效仿快速排序的思想，将数组根据某个值x划分，使得左侧都小于等于x，右侧都大于等于x，这样如果左侧个数为k-1的话，就可以返回x，否则遍历左边或者右边
     * 注意，这里的时间复杂度其实不太容易得出，可参考《算法导论》，最坏的情况下为n^2，随机数据下为n
     * 方法三：使用堆排序
     * <p>
     * 复杂度：n
     */
    public static int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return quickselect(nums, 0, n - 1, n - k);
    }

    private static int quickselect(int[] nums, int l, int r, int k) {
        if (l == r) return nums[k];
        int x = nums[l], i = l - 1, j = r + 1;
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        if (k <= j) return quickselect(nums, l, j, k);
        else return quickselect(nums, j + 1, r, k);
    }

    public static int findKthLargest0(int[] nums, int k) {
        return Arrays.stream(nums).boxed().sorted(Comparator.reverseOrder()).skip(k - 1).findFirst().orElse(0);
    }

}