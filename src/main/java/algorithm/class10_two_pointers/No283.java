package algorithm.class10_two_pointers;

import java.util.Arrays;

import static algorithm.utils.FormatConversion.strToIntArray;

/**
 * 283. Move Zeroes
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-14 00:27
 */
public class No283 {

    public static void main(String[] args) {
        // "[1, 3, 12, 0, 0]"
        int[] array = strToIntArray("[0,1,0,3,12]");
        moveZeroes(array);
        System.out.println(Arrays.toString(array));

        // "[1, 1, 1, 2, 2, 3, 3, 4, 0, 0]"
        int[] array1 = strToIntArray("[0,0,1,1,1,2,2,3,3,4]");
        moveZeroes(array1);
        System.out.println(Arrays.toString(array1));

        // "[0]"
        int[] array2 = strToIntArray("[0]");
        moveZeroes(array2);
        System.out.println(Arrays.toString(array2));

        // "[1, 1, 3, 12, 0]"
        int[] array3 = strToIntArray("[1,1,0,3,12]");
        moveZeroes(array3);
        System.out.println(Arrays.toString(array3));

        // "[1, 1, 2, 3, 12]"
        int[] array4 = strToIntArray("[1,1,2,3,12]");
        moveZeroes(array4);
        System.out.println(Arrays.toString(array4));

        // "[0, 0, 0, 0, 0]"
        int[] array5 = strToIntArray("[0,0,0,0,0]");
        moveZeroes(array5);
        System.out.println(Arrays.toString(array5));
    }

    /**
     * 思路：快慢指针
     * 复杂度：n
     */
    public static void moveZeroes(int[] nums) {
        int left = 0;
        while (left < nums.length && nums[left] != 0) {
            left++;
        }
        int right = left + 1;
        while (right < nums.length) {
            if (nums[right] != 0) {
                nums[left++] = nums[right];
                nums[right] = 0;
            }
            right++;
        }
    }

}