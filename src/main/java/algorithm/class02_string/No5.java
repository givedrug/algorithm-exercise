package algorithm.class02_string;

/**
 * 5. Longest Palindromic Substring
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-06 23:37
 */
public class No5 {

    public static void main(String[] args) {
        // "bab" or "aba"
        System.out.println(longestPalindrome("babad"));
        // "bb"
        System.out.println(longestPalindrome("cbbd"));
    }

    /**
     * 思路：遍历字符，分别将当前字符作为回文中心查找最大回文串（注意奇数回文与偶数回文的差异）
     * 复杂度：n^2
     */
    public static String longestPalindrome(String s) {
        String longestPalindrome = "";
        char[] charArray = s.toCharArray();
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            // 计算奇数回文
            for (int j = 0; i - j > -1 && i + j < length; j++) {
                if (charArray[i - j] == charArray[i + j]) {
                    if (2 * j + 1 > longestPalindrome.length()) {
                        longestPalindrome = new String(charArray, i - j, 2 * j + 1);
                    }
                } else {
                    break;
                }
            }
            // 计算偶数回文
            for (int j = 1; i - j + 1 > -1 && i + j < length; j++) {
                if (charArray[i - j + 1] == charArray[i + j]) {
                    if (2 * j > longestPalindrome.length()) {
                        longestPalindrome = new String(charArray, i - j + 1, 2 * j);
                    }
                } else {
                    break;
                }
            }
        }
        return longestPalindrome;
    }

}