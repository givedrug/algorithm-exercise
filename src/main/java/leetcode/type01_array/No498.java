package leetcode.type01_array;

import java.util.Arrays;

import static leetcode.common.FormatConversion.strToIntArrayArray;

/**
 * 498. Diagonal Traverse
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-06 10:38
 */
public class No498 {

    public static void main(String[] args) {
        // [1,2,4,7,5,3,6,8,9]
        System.out.println(Arrays.toString(findDiagonalOrder(strToIntArrayArray("[[1,2,3],[4,5,6],[7,8,9]]"))));
        // [1,2,3,4]
        System.out.println(Arrays.toString(findDiagonalOrder(strToIntArrayArray("[[1,2],[3,4]]"))));
    }

    /**
     * 思路：注意到，每次同一条对角线上的坐标(i,j)都有i+j为固定值，且下一条比上一条递增1。同时注意每次要调换上下顺序。
     * 复杂度：m*n
     */
    public static int[] findDiagonalOrder(int[][] mat) {
        // 边界判断
        int m = mat.length;
        if (m == 0) {
            return new int[0];
        }
        int n = mat[0].length;
        if (n == 0) {
            return new int[0];
        }

        // 按序输出
        int maxSum = (m - 1) + (n - 1);
        int[] result = new int[m * n];
        int index = 0;
        for (int sum = 0; sum <= maxSum; sum++) {
            if (sum % 2 == 0) {
                // 和为偶数时，从左下到右上
                int i = Math.min(sum, m - 1), j = sum - i;
                while (i >= 0 && j <= n - 1) {
                    result[index++] = mat[i--][j++];
                }
            } else {
                // 和为奇数时，从右上到左下
                int j = Math.min(sum, n - 1), i = sum - j;
                while (j >= 0 && i <= m - 1) {
                    result[index++] = mat[i++][j--];
                }
            }
        }
        return result;
    }

}