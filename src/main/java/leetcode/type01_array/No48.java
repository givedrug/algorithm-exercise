package leetcode.type01_array;

import java.util.Arrays;

import static leetcode.common.FormatConversion.strToIntArrayArray;

/**
 * 48. Rotate Image
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-05 17:07
 */
public class No48 {

    public static void main(String[] args) {
        // [[7,4,1],[8,5,2],[9,6,3]]
        int[][] matrix0 = strToIntArrayArray("[[1,2,3],[4,5,6],[7,8,9]]");
        rotate(matrix0);
        System.out.println(Arrays.deepToString(matrix0));
        // [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
        int[][] matrix1 = strToIntArrayArray("[[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]");
        rotate(matrix1);
        System.out.println(Arrays.deepToString(matrix1));
    }

    /**
     * 思路：直接交换四分之一块(i,j)->(j,n-i)
     * 复杂度：n^2/4
     */
    public static void rotate(int[][] matrix) {
        int length = matrix.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                // 偶数时，交换范围为左上角四分之一块；奇数时，需要额外增加中心交叉十字线的四分之一（除去中心点）
                if ((i < length / 2 && j < length / 2) || (length % 2 == 1 && i == length / 2 && i < j)) {
                    // 交换(i,j)与其旋转90度位置
                    switchMatrix(matrix, i, j, j, length - 1 - i);
                    // 交换(i,j)与其旋转180度位置
                    switchMatrix(matrix, i, j, length - 1 - i, length - 1 - j);
                    // 交换(i,j)与其旋转270度位置
                    switchMatrix(matrix, i, j, length - 1 - j, i);
                }
            }
        }
    }

    /**
     * 交换元素
     *
     * @param matrix
     * @param i0
     * @param j0
     * @param i1
     * @param j1
     */
    private static void switchMatrix(int[][] matrix, int i0, int j0, int i1, int j1) {
        int tmp = matrix[i0][j0];
        matrix[i0][j0] = matrix[i1][j1];
        matrix[i1][j1] = tmp;
    }

}