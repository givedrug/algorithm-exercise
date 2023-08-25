package algorithm.class05_binary_search;

/**
 * 374. Guess Number Higher or Lower
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-31 15:01
 */
public class No374 {

    public static int picked;

    public static void main(String[] args) {
        picked = 6;
        System.out.println(guessNumber(10));
        picked = 1;
        System.out.println(guessNumber(1));
        picked = 1;
        System.out.println(guessNumber(2));
        picked = 1702766719;
        System.out.println(guessNumber(2126753390));
    }

    /**
     * 思路：二分法
     * 复杂度：log(n)
     */
    public static int guessNumber(int n) {
        int left = 0, right = n, middle;
        while (left <= right) {
            middle = left + (right - left) / 2;
            if (guess(middle) == 0) {
                return middle;
            }
            if (guess(middle) > 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }

    private static int guess(int num) {
        return Integer.compare(picked, num);
    }

}