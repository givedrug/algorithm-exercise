package leetcode.type02_string;

/**
 * 28. Find the Index of the First Occurrence in a String
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-11 00:17
 */
public class No28 {

    public static void main(String[] args) {
        // 0
        System.out.println(strStr("sadbutsad", "sad"));
        // -1
        System.out.println(strStr("leetcode", "leeto"));
    }

    /**
     * 思路：KMP
     * 复杂度：n*m
     */
    public static int strStr(String haystack, String needle) {
        char[] h = haystack.toCharArray();
        char[] n = needle.toCharArray();
        int[] next = buildNext(n);
        int tar = 0, pos = 0;
        while (tar < h.length) {
            if (h[tar] == n[pos]) {
                tar++;
                pos++;
            } else if (pos != 0) {
                pos = next[pos - 1];
            } else {
                tar++;
            }

            if (pos == n.length) {
                return tar - pos;
            }
        }
        return -1;
    }

    private static int[] buildNext(char[] p) {
        int[] next = new int[p.length + 1];
        int x = 1, now = 0;
        while (x < p.length) {
            if (p[x] == p[now]) {
                now++;
                next[x] = now;
                x++;
            } else if (now != 0) {
                now = next[now - 1];
            } else {
                x++;
                next[x] = now;
            }
        }
        return next;
    }

}