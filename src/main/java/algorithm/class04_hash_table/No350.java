package algorithm.class04_hash_table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static algorithm.common.FormatConversion.strToIntArray;

/**
 * 350. Intersection of Two Arrays II
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-08-04 17:08
 */
public class No350 {

    public static void main(String[] args) {
        // [2,2]
        System.out.println(Arrays.toString(intersect(strToIntArray("[1,2,2,1]"), strToIntArray("[2,2]"))));
        // [9,4]
        System.out.println(Arrays.toString(intersect(strToIntArray("[4,9,5]"), strToIntArray("[9,4,9,8,4]"))));
    }

    /**
     * 思路：使用map
     * 复杂度：n
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i : nums2) {
            if (map.containsKey(i)) {
                result.add(i);
                Integer count = map.get(i);
                if (count == 1) {
                    map.remove(i);
                } else {
                    map.put(i, count - 1);
                }
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

}