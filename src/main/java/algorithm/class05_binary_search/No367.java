package algorithm.class05_binary_search;

/**
 * 367. Valid Perfect Square
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-08-03 15:18
 */
public class No367 {

    public static void main(String[] args) {
        // false
        System.out.println(isPerfectSquare(2000105819));
        // true
        System.out.println(isPerfectSquare(16));
        // false
        System.out.println(isPerfectSquare(14));
    }

    /**
     * 思路：通过幂函数找到左右边界，然后二分确定是否为完全平方数
     * 复杂度：log(n)
     */
    public static boolean isPerfectSquare(int num) {
        if (num < 0) {
            return false;
        }

        if (num == 0 || num == 1) {
            return true;
        }

        long boundary = 1;
        while (boundary * boundary < num) {
            boundary *= 2;
        }

        long left = boundary / 2, right = boundary;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long temp = mid * mid;
            if (num == temp) {
                return true;
            }
            if (num > temp) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

}