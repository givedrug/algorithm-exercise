package leetcode.type12_two_pointers;

import java.util.Arrays;
import java.util.stream.IntStream;

import static leetcode.common.FormatConversion.strToIntArray;

/**
 * 561. Array Partition
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-11 15:43
 */
public class No561 {

    public static void main(String[] args) {
        // 4
        System.out.println(arrayPairSum(strToIntArray("[1,4,3,2]")));
        // 9
        System.out.println(arrayPairSum(strToIntArray("[6,2,6,5,1,2]")));
    }

    /**
     * 思路：先排序，然后取所有奇数位数字和
     * 当然，如果可以确定数字都为整数，还可以通过bitmap来统计出现次数，相当于排序，这样就可以将复杂度降为n
     * 复杂度：n*log(n)
     */
    public static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        return IntStream.range(0, nums.length).filter(i -> i % 2 == 0).map(i -> nums[i]).sum();
    }

}