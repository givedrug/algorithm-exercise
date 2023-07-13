package leetcode.type17_math_and_bit_manipulation;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. Pascal's Triangle
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-13 12:33
 */
public class No118 {

    public static void main(String[] args) {
        // [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
        System.out.println(generate(5));
        // [[1]]
        System.out.println(generate(1));
    }

    /**
     * 思路：按杨辉三角定义生成
     * 复杂度：n^2
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> previous = new ArrayList<>(1);
        previous.add(1);
        result.add(previous);
        for (int i = 2; i <= numRows; i++) {
            List<Integer> current = new ArrayList<>(i);
            for (int j = 0; j < i; j++) {
                int left = j == 0 ? 0 : previous.get(j - 1);
                int right = j == i - 1 ? 0 : previous.get(j);
                current.add(j, left + right);
            }
            result.add(current);
            previous = current;
        }
        return result;
    }

}