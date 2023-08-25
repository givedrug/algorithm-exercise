package algorithm.class04_hash_table;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static algorithm.common.FormatConversion.strToIntArray;

/**
 * 349. Intersection of Two Arrays
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-25 11:38
 */
public class No349 {

    public static void main(String[] args) {
        // [2]
        System.out.println(Arrays.toString(intersection(strToIntArray("[1,2,2,1]"), strToIntArray("[2,2]"))));
        // [9,4]
        System.out.println(Arrays.toString(intersection(strToIntArray("[4,9,5]"), strToIntArray("[9,4,9,8,4]"))));
    }

    /**
     * 思路：使用set
     * 复杂度：n
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        Set<Integer> result = new HashSet<>();
        for (int i : nums2) {
            if (set.contains(i)) {
                result.add(i);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

}