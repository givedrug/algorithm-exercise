package leetcode.type05_binary_search;

/**
 * 278. First Bad Version
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-08-01 11:07
 */
public class No278 {

    public static int bad = 0;

    public static void main(String[] args) {
        bad = 5;
        System.out.println(firstBadVersion(5));
    }

    /**
     * 思路：二分法
     * 复杂度：log(n)
     */
    public static int firstBadVersion(int n) {
        if (isBadVersion(1)) {
            return 1;
        }
        int left = 1, right = n, middle;
        while (left < right) {
            middle = left + (right - left) / 2;
            boolean curr = isBadVersion(middle);
            boolean next = isBadVersion(middle + 1);
            if (!curr && next) {
                return middle + 1;
            }
            if (curr) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return 0;
    }

    private static boolean isBadVersion(int version) {
        if (version >= bad) {
            return true;
        }
        return false;
    }

}