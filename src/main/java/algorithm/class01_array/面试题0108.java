package algorithm.class01_array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static algorithm.utils.FormatConversion.strToIntArrayArray;

/**
 * 面试题 01.08. Zero Matrix LCCI
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-05 19:00
 */
public class 面试题0108 {

    public static void main(String[] args) {
        // [
        //  [1,0,1],
        //  [0,0,0],
        //  [1,0,1]
        //]
        int[][] matrix0 = strToIntArrayArray("[\n" +
            "  [1,1,1],\n" +
            "  [1,0,1],\n" +
            "  [1,1,1]\n" +
            "]");
        setZeroes(matrix0);
        System.out.println(Arrays.deepToString(matrix0));

        // [
        //  [0,0,0,0],
        //  [0,4,5,0],
        //  [0,3,1,0]
        //]
        int[][] matrix1 = strToIntArrayArray("[\n" +
            "  [0,1,2,0],\n" +
            "  [3,4,5,2],\n" +
            "  [1,3,1,5]\n" +
            "]");
        setZeroes(matrix1);
        System.out.println(Arrays.deepToString(matrix1));
    }

    /**
     * 思路：第一遍找出所有0元素的行号与列号，第二遍置零
     * 复杂度：m*n
     */
    public static void setZeroes(int[][] matrix) {
        Set<Integer> xSet = new HashSet<>();
        Set<Integer> ySet = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    xSet.add(i);
                    ySet.add(j);
                }
            }
        }
        xSet.forEach(x -> {
            for (int i = 0; i < matrix[x].length; i++) {
                matrix[x][i] = 0;
            }
        });
        ySet.forEach(y -> {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][y] = 0;
            }
        });
    }

}