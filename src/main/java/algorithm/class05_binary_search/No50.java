package algorithm.class05_binary_search;

import java.util.ArrayList;
import java.util.List;

/**
 * 50. Pow(x, n)
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-08-03 14:03
 */
public class No50 {

    public static void main(String[] args) {
        // 1.0
        System.out.println(myPow(-1.00000, -2147483648));
        // 0.0
        System.out.println(myPow(2.00000, -2147483648));
        // 1024.00000
        System.out.println(myPow(2.00000, 10));
        // 9.26100
        System.out.println(myPow(2.10000, 3));
        // 0.25000
        System.out.println(myPow(2.00000, -2));
    }

    /**
     * 思路：将n转为二进制，并保存中间结果x^1、x^2、x^4、x^8、x^16……，然后计算n的二进制为1的位对应的中间结果的乘积（如果n为负数则使用除法）
     * 需要注意Integer.MIN_VALUE的特殊处理
     * 复杂度：log(n)
     */
    public static double myPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }

        boolean positive = n > 0;
        int m = n == Integer.MIN_VALUE ? Integer.MAX_VALUE : Math.abs(n);
        double result = 1.0;
        List<Double> intermediateResults = new ArrayList<>();

        double curr = x;
        double last = x;
        while (m != 0) {
            if (intermediateResults.size() != 0) {
                curr = last * last;
                last = curr;
            }
            intermediateResults.add(curr);
            if (m % 2 == 1) {
                if (positive) {
                    result *= curr;
                } else {
                    result /= curr;
                }
            }
            m = m / 2;
        }

        return n != Integer.MIN_VALUE ? result : result * (1 / x);
    }

}