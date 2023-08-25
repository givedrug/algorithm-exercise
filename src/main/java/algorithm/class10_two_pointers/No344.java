package algorithm.class10_two_pointers;

import static algorithm.common.FormatConversion.strToCharArray;

/**
 * 344. Reverse String
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-11 00:39
 */
public class No344 {

    public static void main(String[] args) {
        char[] chars0 = strToCharArray("[\"h\",\"e\",\"l\",\"l\",\"o\"]");
        reverseString(chars0);
        // ["o","l","l","e","h"]
        System.out.println(chars0);

        char[] chars1 = strToCharArray("[\"H\",\"a\",\"n\",\"n\",\"a\",\"h\"]");
        reverseString(chars1);
        // ["h","a","n","n","a","H"]
        System.out.println(chars1);
    }

    /**
     * 思路：对撞双指针
     * 复杂度：n
     */
    public static void reverseString(char[] s) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            if (s[l] != s[r]) {
                char tmp = s[l];
                s[l] = s[r];
                s[r] = tmp;
            }
            l++;
            r--;
        }
    }

}