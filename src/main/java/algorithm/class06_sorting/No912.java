package algorithm.class06_sorting;

import java.util.Arrays;

import static algorithm.utils.FormatConversion.strToIntArray;

/**
 * 912. Sort an Array
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-10-12 11:09
 */
public class No912 {

    public static void main(String[] args) {

        // [1,2,3,5]
        System.out.println(Arrays.toString(sortArray(strToIntArray("[5,2,3,1]"))));

        // [0,0,1,1,2,5]
        System.out.println(Arrays.toString(sortArray(strToIntArray("[5,1,1,2,0,0]"))));

    }

    /**
     * 思路：快排
     * 复杂度：n*log(n)
     */
    public static int[] sortArray(int[] nums) {
        return Arrays.stream(nums).sorted().toArray();
    }

}