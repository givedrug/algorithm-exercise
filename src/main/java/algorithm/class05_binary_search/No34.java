package algorithm.class05_binary_search;

import java.util.Arrays;

import static algorithm.utils.FormatConversion.strToIntArray;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-08-01 17:35
 */
public class No34 {

    public static void main(String[] args) {
        // [3,4]
        System.out.println(Arrays.toString(searchRange(strToIntArray("[5,7,7,8,8,10]"), 8)));
        // [1,2]
        System.out.println(Arrays.toString(searchRange(strToIntArray("[5,7,7,8,8,10]"), 7)));
        // [0,0]
        System.out.println(Arrays.toString(searchRange(strToIntArray("[5,7,7,8,8,10]"), 5)));
        // [5,5]
        System.out.println(Arrays.toString(searchRange(strToIntArray("[5,7,7,8,8,10]"), 10)));
        // [-1,-1]
        System.out.println(Arrays.toString(searchRange(strToIntArray("[5,7,7,8,8,10]"), 6)));
        // [-1,-1]
        System.out.println(Arrays.toString(searchRange(strToIntArray("[]"), 8)));
    }

    /**
     * 思路：先通过二分找到一个符合的值，然后用二分分别找到左右边界
     * 另外，其实也可以直接分两次找到左右边界，而不用非要先找到一个符合的值
     * 复杂度：log(n)
     */
    public static int[] searchRange(int[] nums, int target) {
        int length = nums.length;
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        if (length == 0) {
            return result;
        }
        // 先随便找到一个目标值
        int left = 0, right = length - 1, middle = -1;
        while (left <= right) {
            middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                break;
            }
            if (nums[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        // 没找到
        if (left > right) {
            return result;
        }
        // 查找左边界
        result[0] = findLeft(nums, left, middle, target);
        // 查找右边界
        result[1] = findRight(nums, middle, right, target);
        return result;
    }

    private static int findLeft(int[] nums, int left, int right, int target) {
        if (nums[0] == target) {
            return 0;
        }
        int middle;
        while (left <= right) {
            middle = left + (right - left) / 2;
            if (nums[middle] == target && nums[middle - 1] < target) {
                return middle;
            }
            if (nums[middle] < target && nums[middle + 1] == target) {
                return middle + 1;
            }
            if (nums[middle] == target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }

    private static int findRight(int[] nums, int left, int right, int target) {
        if (nums[nums.length - 1] == target) {
            return nums.length - 1;
        }
        int middle;
        while (left <= right) {
            middle = left + (right - left) / 2;
            if (nums[middle] == target && nums[middle + 1] > target) {
                return middle;
            }
            if (nums[middle] > target && nums[middle - 1] == target) {
                return middle - 1;
            }
            if (nums[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }

}