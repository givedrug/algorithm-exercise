package leetcode.type02_string;

/**
 * 557. Reverse Words in a String III
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-13 19:17
 */
public class No557 {

    public static void main(String[] args) {
        // "s'teL ekat edoCteeL tsetnoc"
        System.out.println(reverseWords("Let's take LeetCode contest"));
        // "doG gniD"
        System.out.println(reverseWords("God Ding"));
    }

    /**
     * 思路：滑动窗口
     * 复杂度：n
     */
    public static String reverseWords(String s) {
        char[] charArray = s.toCharArray();
        int left = 0, right = 0;
        while (right <= charArray.length) {
            if (right == charArray.length && left < right) {
                reverseWord(charArray, left, right - 1);
                break;
            }
            if (charArray[right] != ' ') {
                right++;
            } else {
                reverseWord(charArray, left, right - 1);
                right++;
                left = right;
            }
        }
        return new String(charArray);
    }

    /**
     * 翻转一个word
     *
     * @param charArray
     * @param left
     * @param right
     */
    private static void reverseWord(char[] charArray, int left, int right) {
        while (left < right) {
            char tmp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = tmp;
            left++;
            right--;
        }
    }

}