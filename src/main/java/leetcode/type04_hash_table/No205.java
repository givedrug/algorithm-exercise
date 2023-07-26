package leetcode.type04_hash_table;

import java.util.HashMap;
import java.util.Map;

/**
 * 205. Isomorphic Strings
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-26 10:03
 */
public class No205 {

    public static void main(String[] args) {
        // true
        System.out.println(isIsomorphic("egg", "add"));
        // false
        System.out.println(isIsomorphic("foo", "bar"));
        // true
        System.out.println(isIsomorphic("paper", "title"));
        // false
        System.out.println(isIsomorphic("badc", "baba"));
    }

    /**
     * 思路：保存两字符串的对应字符的映射关系
     * 复杂度：n
     */
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int length = s.length();
        char[] sca = s.toCharArray();
        char[] tca = t.toCharArray();
        Map<Character, Character> mapStoT = new HashMap<>();
        Map<Character, Character> mapTtoS = new HashMap<>();
        for (int i = 0; i < length; i++) {
            if (mapStoT.containsKey(sca[i])) {
                if (mapStoT.get(sca[i]) != tca[i]) {
                    return false;
                }
            } else {
                mapStoT.put(sca[i], tca[i]);
            }
            if (mapTtoS.containsKey(tca[i])) {
                if (mapTtoS.get(tca[i]) != sca[i]) {
                    return false;
                }
            } else {
                mapTtoS.put(tca[i], sca[i]);
            }
        }
        return true;
    }

}