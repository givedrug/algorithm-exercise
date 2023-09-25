package algorithm.class02_string;

import java.util.ArrayList;
import java.util.List;
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
     * 思路：顺序遍历，跳过空格，遇到非空格后记录单词，并将单词放入list，反序后join空格并返回
     * 或者可以直接使用java8，分割、过滤、反转、拼接
     * 一行代码：
     * Arrays.stream(s.split("\\s+"))
     * .filter(e -> !"".equals(e))
     * .sorted((pre, next) -> -1)
     * .collect(Collectors.joining(" "));
     * 复杂度：n
     */
    public static String reverseWords(String s) {
        char[] charArray = s.toCharArray();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ' ') {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            while (i < charArray.length && charArray[i] != ' ') {
                sb.append(charArray[i++]);
            }
            if (sb.length() > 0) {
                list.add(0, sb.toString());
            }
        }
        return list.stream().collect(Collectors.joining(" "));
    }

}