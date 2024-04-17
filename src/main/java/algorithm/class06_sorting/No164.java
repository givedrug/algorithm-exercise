package algorithm.class06_sorting;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import static algorithm.utils.FormatConversion.strToIntArray;

/**
 * 164. Maximum Gap
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2024-04-15 17:21
 */
public class No164 {

    @Test
    public void test() {
        Assert.assertEquals(3, maximumGap(strToIntArray("[3,6,9,1]")));
        Assert.assertEquals(0, maximumGap(strToIntArray("[10]")));
    }

    /**
     * 思路：先基数排序（或者桶排序，因为有时间复杂度要求），然后找到最大间距
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        int[] sorted = radixSort(nums);
        int maxGap = 0;
        for (int i = 1; i < sorted.length; i++) {
            maxGap = Math.max(maxGap, sorted[i] - sorted[i - 1]);
        }

        return maxGap;
    }

    private int[] radixSort(int[] array) {
        if (array.length < 2) {
            return array;
        }

        int maxValue = array[0];
        for (int element : array) {
            if (element > maxValue) {
                maxValue = element;
            }
        }

        int k = 1;
        while (maxValue / 10 != 0) {
            maxValue = maxValue / 10;
            k += 1;
        }
        for (int i = 0; i < k; i++) {
            List<List<Integer>> radix = new ArrayList<>();
            for (int b = 0; b < 10; b++) {
                radix.add(new ArrayList<>());
            }
            for (int element : array) {
                int idx = (element / (int) Math.pow(10, i)) % 10;
                radix.get(idx).add(element);
            }
            int idx = 0;
            for (List<Integer> list : radix) {
                for (int element : list) {
                    array[idx++] = element;
                }
            }
        }
        return array;
    }

}