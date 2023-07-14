package leetcode.type17_math_and_bit_manipulation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 119. Pascal's Triangle II
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-13 14:28
 */
public class No119 {

    public static void main(String[] args) {
        // [1,3,3,1]
        System.out.println(getRow(3));
        // [1]
        System.out.println(getRow(0));
        // [1,1]
        System.out.println(getRow(1));
        // [1,13,78,286,715,1287,1716,1716,1287,715,286,78,13,1]
        System.out.println(getRow(13));
    }

    /**
     * 方法一:
     * <p>
     * 思路：第n行第m个数字为n!/m!/(n-m)!，与第m-1个数字的倍数是(n-m+1)/m
     * 注意不要使用int，会溢出，需要在乘法时先转为long
     * 复杂度：n
     */
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>(rowIndex + 1);
        row.add(1);
        for (int i = 1; i < rowIndex + 1; i++) {
            row.add((int) ((long) row.get(i - 1) * (rowIndex - i + 1) / i));
        }
        return row;
    }

    /**
     * 方法二：
     * <p>
     * 思路：先求出n以内的阶乘，则第n行第m个数字为n!/m!/(n-m)!
     * 注意不要使用int，会溢出，但leetcode不能使用BigInteger
     * 复杂度：n
     */
    public static List<Integer> getRowMultiple(int rowIndex) {
        BigInteger[] factorial = getFactorialArray(rowIndex);
        List<Integer> row = new ArrayList<>(rowIndex + 1);
        for (int i = 0; i < rowIndex + 1; i++) {
            row.add(factorial[rowIndex].divide(factorial[i]).divide(factorial[rowIndex - i]).intValue());
        }
        return row;
    }

    private static BigInteger[] getFactorialArray(int rowIndex) {
        BigInteger[] array = new BigInteger[rowIndex + 1];
        array[0] = BigInteger.valueOf(1);
        for (int i = 1; i < rowIndex + 1; i++) {
            array[i] = array[i - 1].multiply(BigInteger.valueOf(i));
        }
        return array;
    }

}