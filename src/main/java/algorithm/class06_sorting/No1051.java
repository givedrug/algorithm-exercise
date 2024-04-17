package algorithm.class06_sorting;

import org.junit.Assert;
import org.junit.Test;

import static algorithm.utils.FormatConversion.strToIntArray;

/**
 * 1051. Height Checker
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2024-04-17 11:22
 */
public class No1051 {

    @Test
    public void test() {
        Assert.assertEquals(3, heightChecker(strToIntArray("[1,1,4,2,1,3]")));
        Assert.assertEquals(5, heightChecker(strToIntArray("[5,1,2,3,4]")));
        Assert.assertEquals(0, heightChecker(strToIntArray("[1,2,3,4,5]")));
    }

    /**
     * 思路：直接排序，然后逐项对比。因为元素范围比较小只有100个，所以使用计数排序可以获得O(n)的排序时间。
     * 时间复杂度：O(n)
     * 空间复杂度：O(n+k)
     */
    public int heightChecker(int[] heights) {
        // 进行计数排序
        int[] bucket = new int[101];
        for (int height : heights) {
            bucket[height]++;
        }

        // 计算不符合要求的次数
        int count = 0, index = 0;
        for (int i = 1; i < bucket.length; i++) {
            while (bucket[i]-- > 0) {
                if (heights[index++] != i) {
                    count++;
                }
            }
        }
        return count;
    }

}