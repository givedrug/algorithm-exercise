package algorithm.class05_binary_search;

/**
 * 69. Sqrt(x)
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-31 14:23
 */
public class No69 {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            System.out.println(i + ":" + mySqrt(i));
        }

        // 46339
        System.out.println(mySqrt(2147395599));

        // 46340
        System.out.println(mySqrt(2147483647));

    }

    /**
     * 思路：二分法
     * 另外，由均值不等式，x和1的算术平均数一定大于几何平均数，可以从1到(x+1)/2为左右边界
     * 另外，官方解答中，还可以使用牛顿迭代法求解
     * 复杂度：log(n)
     */
    public static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        // 避免溢出，相当于right = (x + 1) / 2
        int left = 1, right = x % 2 == 0 ? x / 2 : x / 2 + 1, middle;
        while (left <= right) {
            // 避免溢出，相当于(left+right)/2
            middle = left + (right - left) / 2;
            int quotient0 = x / middle;
            int quotient1 = x / (middle + 1);
            // 避免溢出，相当于middle*middle<=x且(middle+1)*(middle+1)>x
            if (middle <= quotient0 && middle + 1 > quotient1) {
                return middle;
            }
            if (middle < quotient0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }

}