package algorithm.class04_hash_table;

import java.util.HashMap;
import java.util.Map;

import static algorithm.utils.FormatConversion.strToIntArray;

/**
 * 169. Majority Element
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2024-04-09 11:00
 */
public class No169 {

    public static void main(String[] args) {
        // 3
        System.out.println(majorityElement(strToIntArray("[3,2,3]")));
        // 2
        System.out.println(majorityElement(strToIntArray("[2,2,1,1,1,2,2]")));
    }

    /**
     * 思路：通过map统计每个数字出现个数，然后返回个数最大的数字
     * 复杂度：O(n)
     */
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> itemMap = new HashMap<>();
        for (int num : nums) {
            if (itemMap.containsKey(num)) {
                itemMap.put(num, itemMap.get(num) + 1);
            } else {
                itemMap.put(num, 0);
            }
        }
        return itemMap.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }


}