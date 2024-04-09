package algorithm.class06_sorting;

import java.util.Arrays;

import static algorithm.utils.FormatConversion.strToIntArray;

/**
 * Sorted Merge LCCI
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2024-04-09 11:38
 */
public class 面试题10_01 {

    public static void main(String[] args) {
        int[] A = strToIntArray("[1,2,3,0,0,0]");
        int[] B = strToIntArray("[2,5,6]");
        merge(A, 3, B, 3);
        // [1,2,2,3,5,6]
        System.out.println(Arrays.toString(A));
    }

    /**
     * 思路：逆向双指针，从后向前遍历，这样可以节省空间
     * 时间复杂度：O(m+n)
     * 空间复杂度：O(1)
     */
    public static void merge(int[] A, int m, int[] B, int n) {
        int a = m - 1;
        int b = n - 1;
        int c = A.length - 1;
        while (a >= 0 && b >= 0) {
            if (B[b] > A[a]) {
                A[c--] = B[b--];
            } else {
                A[c--] = A[a--];
            }
        }
        if (a < 0) {
            while (b >= 0) {
                A[c--] = B[b--];
            }
        }
        if (b < 0) {
            while (a >= 0) {
                A[c--] = A[a--];
            }
        }
    }

}