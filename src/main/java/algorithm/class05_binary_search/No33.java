package algorithm.class05_binary_search;

import static algorithm.utils.FormatConversion.strToIntArray;

/**
 * 33. Search in Rotated Sorted Array
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-31 15:13
 */
public class No33 {

    public static void main(String[] args) {
        // 4
        System.out.println(search(strToIntArray("[4,5,6,7,0,1,2]"), 0));
        // -1
        System.out.println(search(strToIntArray("[4,5,6,7,0,1,2]"), 3));
        // -1
        System.out.println(search(strToIntArray("[1]"), 2));
        // 1
        System.out.println(search(strToIntArray("[1,3]"), 3));
    }

    /**
     * 思路：结合153与704，使用二分法，先找到最小数字索引，然后根据target值和nums边界的大小关系，再使用二分查找找到target
     * 另外，官方解答中，可以使用一次二分查找解决，与仅对比中间值与目标值不同，此时需要对比左右边界、中间值与目标值的关系（必然有一侧是严格有序的）来确定二分走向，而且还要小心临近目标时的处理
     * 复杂度：log(n)
     */
    public static int search(int[] nums, int target) {
        int minIndex = findMinIndex(nums);
        if (minIndex == 0) {
            return binarySearch(nums, 0, nums.length - 1, target);
        }
        if (target >= nums[0]) {
            return binarySearch(nums, 0, minIndex - 1, target);
        }
        if (target <= nums[nums.length - 1]) {
            return binarySearch(nums, minIndex, nums.length - 1, target);
        }
        return -1;
    }

    private static int binarySearch(int[] nums, int left, int right, int target) {
        int middle;
        while (left <= right) {
            middle = (left + right) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            // 因为middle一定不是目标值，所以左右指针可以跳过middle，加一或减一
            if (nums[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }

    private static int findMinIndex(int[] nums) {
        if (nums[0] <= nums[nums.length - 1]) {
            return 0;
        }
        int left = 0, right = nums.length - 1;
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[left]) {
                left = mid;
            }
            if (nums[mid] < nums[right]) {
                right = mid;
            }
        }
        return right;
    }

}