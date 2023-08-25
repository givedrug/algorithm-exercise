package algorithm.class05_binary_search;

import static algorithm.common.FormatConversion.strToIntArray;

/**
 * 410. Split Array Largest Sum
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-08-23 18:00
 */
public class No410 {

    public static void main(String[] args) {
        // 18
        System.out.println(splitArray(strToIntArray("[7,2,5,10,8]"), 4));
        // 9
        System.out.println(splitArray(strToIntArray("[1,2,3,4,5]"), 2));
    }

    /**
     * 思路：本题可以使用动态规划来实现，但需要求出所有子数组在不同分割情况下的最小最大和，因此效率较低。
     * <p>
     * 参考官方解答，还有一种基于二分查找的方案，复杂度更低
     * 注意到，所有子数组的和的取值范围在max(nums[])到sum(nums[])之间，可以对这个和进行二分查找，
     * 找到一个最小值x，
     * 当大于等于x时，数组存在至少一个分割方案，满足所有的子数组和都小于等于x
     * 当小于x时，数组不存在一种分割方案，满足所有的子数组和都小于等于x
     * 于是，问题进一步转变为，给定一个x，判断是否至少存在一个分割方案的问题，这时可以使用贪心法来计算
     * 从左到右遍历数组，并计算累加和，如果和超过x，则count加一，然后重新累加，直到count大于k（失败，即不存在），或者数组遍历完为止，count依旧小于等于k（成功，即存在）。
     * <p>
     * 另外，最开始的时候，我想到一种通过比例来切割的方案，
     * 比如说，要分成3份，那么我先按1:2来分割数组，找到左右两边比例最接近1:2的分割方案（同时计算2:1的方案，左右都要考虑）。
     * 然后记录"1"的和，以及继续将"2"的部分按1:1分割，这样就可以直接得出最终结果，甚至最优切割方案。
     * 但是，局部最优解不一定是整体最优解，考虑这样一个反例：
     * [100,1,101,99]，k=3的分割方案
     * 找到1:2的分界点，从左到右100/(1+101+99)=0.497512438(相差0.002487562)，101/(101+99)=0.505(相差0.005)，从右向左99/(100+1+101)=0.49009901(相差0.00990099)，显然应该分割成
     * [100],[1,101,99]
     * 继续[1,101,99]，k=2的分割方案
     * 找到1:1的分界点，显示[1,101],[99]最符合
     * 最终[100,1,101,99]分割为[100],[1,101],[99]，最大和为102
     * 但显然分割为[100,1],[101],[99]，最大和为101是最优分割方案。
     * 所以按比例分割虽然局部考虑是最优分割方案，但不能保证是全局最优方案。
     * 复杂度：n*log(m)，其中m=max(nums[])-sum(nums[])
     */
    public static int splitArray(int[] nums, int m) {
        // 官方解答
        int left = 0, right = 0;
        for (int i = 0; i < nums.length; i++) {
            right += nums[i];
            if (left < nums[i]) {
                left = nums[i];
            }
        }
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (check(nums, mid, m)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static boolean check(int[] nums, int x, int m) {
        int sum = 0;
        int cnt = 1;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] > x) {
                cnt++;
                sum = nums[i];
            } else {
                sum += nums[i];
            }
        }
        return cnt <= m;
    }

}