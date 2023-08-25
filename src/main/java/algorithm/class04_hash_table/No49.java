package algorithm.class04_hash_table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static algorithm.common.FormatConversion.strToStringArray;

/**
 * 49. Group Anagrams
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-26 11:05
 */
public class No49 {

    public static void main(String[] args) {
        // [["bat"],["nat","tan"],["ate","eat","tea"]]
        System.out.println(groupAnagrams(strToStringArray("[\"eat\",\"tea\",\"tan\",\"ate\",\"nat\",\"bat\"]")));
        // [[""]]
        System.out.println(groupAnagrams(strToStringArray("[\"\"]")));
        // [["a"]]
        System.out.println(groupAnagrams(strToStringArray("[\"a\"]")));
    }

    /**
     * 思路：生成每个字符串的排序后字符串，通过hash表做映射
     * 复杂度：n*m*log(m)
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String orderedStr = getOrderedStr(str);
            if (map.containsKey(orderedStr)) {
                map.get(orderedStr).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(orderedStr, list);
            }
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 获取排序后字符串
     *
     * @param str
     * @return
     */
    private static String getOrderedStr(String str) {
        return str.chars().mapToObj(c -> (char) c).sorted().map(Objects::toString).collect(Collectors.joining());
    }

}