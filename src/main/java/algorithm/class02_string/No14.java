package algorithm.class02_string;

import static algorithm.utils.FormatConversion.strToStringArray;

/**
 * 14. Longest Common Prefix
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-06 15:33
 */
public class No14 {

    public static void main(String[] args) {
        // "fl"
        System.out.println(longestCommonPrefix(strToStringArray("[\"flower\",\"flow\",\"flight\"]")));
        // ""
        System.out.println(longestCommonPrefix(strToStringArray("[\"dog\",\"racecar\",\"car\"]")));
        // "a"
        System.out.println(longestCommonPrefix(strToStringArray("[\"ab\", \"a\"]")));
    }

    /**
     * 思路：遍历String数组，每个String转为char数组，两两获取公共前缀。
     * （还有另一种方法，先比较所有字符串的第一个字符，然后比较所有字符串的第二个字符……，这两种方法复杂度相同）
     * 复杂度：n（所有字符的个数）
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        char[] commonPrefix = strs[0].toCharArray();
        // 记录界限，起始值为第一个字符串的长度
        int bound = commonPrefix.length;
        if (bound == 0) {
            return "";
        }

        // 从第二个字符串开始遍历，依次与commonPrefix进行对比，缩短bound值
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() == 0) {
                return "";
            } else {
                char[] charArray = strs[i].toCharArray();
                int currentBound = Math.min(charArray.length, bound);
                for (int j = 0; j < currentBound; j++) {
                    if (charArray[j] != commonPrefix[j]) {
                        if (j == 0) {
                            return "";
                        }
                        bound = j;
                        break;
                    } else {
                        bound = j + 1;
                    }
                }
            }
        }

        // 截取并返回
        return new String(commonPrefix, 0, bound);
    }

}