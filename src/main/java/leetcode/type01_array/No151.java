package leetcode.type01_array;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 151. Reverse Words in a String
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-08 18:47
 */
public class No151 {

    public static void main(String[] args) {
        // "blue is sky the"
        System.out.println(reverseWords("the sky is blue"));
        // "world hello"
        System.out.println(reverseWords("  hello world  "));
        // "example good a"
        System.out.println(reverseWords("a good   example"));
    }

    /**
     * 思路：使用java8，分割、过滤、反转、拼接
     * 复杂度：？
     */
    public static String reverseWords(String s) {
        return Arrays.stream(s.split("\\s+"))
                .filter(e -> !"".equals(e))
                .sorted((pre, next) -> -1)
                .collect(Collectors.joining(" "));
    }

}