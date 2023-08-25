package algorithm.class04_hash_table;

import java.util.HashMap;
import java.util.Map;

import static algorithm.utils.FormatConversion.strToIntArray;

/**
 * 454. 4Sum II
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-28 15:30
 */
public class No454 {

    public static void main(String[] args) {
        // 2
        System.out.println(fourSumCount(strToIntArray("[1,2]"), strToIntArray("[-2,-1]"), strToIntArray("[-1,2]"), strToIntArray("[0,2]")));
        // 1
        System.out.println(fourSumCount(strToIntArray("[0]"), strToIntArray("[0]"), strToIntArray("[0]"), strToIntArray("[0]")));
        // 17
        System.out.println(fourSumCount(strToIntArray("[0,1,-1]"), strToIntArray("[-1,1,0]"), strToIntArray("[0,0,1]"), strToIntArray("[-1,1,1]")));
    }

    /**
     * 思路：分别计算数组1、2和数组3、4的所有和的统计结果map，然后遍历和为0的计数
     * 另外，如果数组重复值多的话，还可以继续优化，先统计数字出现个数，再生成统计map
     * 复杂度：n^2
     */
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map12 = getSumMap(nums1, nums2);
        Map<Integer, Integer> map34 = getSumMap(nums3, nums4);
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map12.entrySet()) {
            Integer key = entry.getKey();
            if (map34.containsKey(-key)) {
                count += entry.getValue() * map34.get(-key);
            }
        }
        return count;
    }

    /**
     * 获取两个数组的不同值的和的个数
     *
     * @param a
     * @param b
     * @return
     */
    private static Map<Integer, Integer> getSumMap(int[] a, int[] b) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : a) {
            for (int j : b) {
                int sum = i + j;
                if (map.containsKey(sum)) {
                    map.put(sum, map.get(sum) + 1);
                } else {
                    map.put(sum, 1);
                }
            }
        }
        return map;
    }

}