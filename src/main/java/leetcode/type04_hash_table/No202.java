package leetcode.type04_hash_table;

import java.util.HashSet;
import java.util.Set;

/**
 * 202. Happy Number
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-25 11:11
 */
public class No202 {

    public static void main(String[] args) {
        // true
        System.out.println(isHappy(19));
        // false
        System.out.println(isHappy(2));
    }

    /**
     * 思路：使用hashSet
     * 另外，官方解答中还提出可以使用快慢指针和链表来实现，等价于找到链表是否有环
     * 复杂度：？
     */
    public static boolean isHappy(int n) {
        Set<Integer> hashSet = new HashSet<>();
        hashSet.add(n);
        while (true) {
            n = squaresSum(n);
            if (n == 1) {
                return true;
            }
            if (hashSet.contains(n)) {
                return false;
            }
            hashSet.add(n);
        }
    }

    /**
     * 获取数字各位平方和
     *
     * @param n
     * @return
     */
    private static int squaresSum(int n) {
        int sum = 0;
        while (n > 0) {
            int curr = n % 10;
            sum += curr * curr;
            n /= 10;
        }
        return sum;
    }

}