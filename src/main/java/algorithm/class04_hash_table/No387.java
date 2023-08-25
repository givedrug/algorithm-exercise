package algorithm.class04_hash_table;

import java.util.HashMap;
import java.util.Map;

/**
 * 387. First Unique Character in a String
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-26 10:38
 */
public class No387 {

    public static void main(String[] args) {
        // 0
        System.out.println(firstUniqChar("algorithm"));
        // 2
        System.out.println(firstUniqChar("loveleetcode"));
        // -1
        System.out.println(firstUniqChar("aabb"));
    }

    /**
     * 思路：使用map计数，还可以使用Ascii码数组计数，速度更快
     * 复杂度：n
     */
    public static int firstUniqChar(String s) {
        int index = -1;
        Map<Character, Integer> map = new HashMap<>();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (map.containsKey(charArray[i])) {
                map.put(charArray[i], map.get(charArray[i]) + 1);
            } else {
                map.put(charArray[i], 1);
            }
        }
        for (int i = 0; i < charArray.length; i++) {
            if (map.get(charArray[i]) == 1) {
                return i;
            }
        }
        return index;
    }

}