package algorithm.class05_binary_search;

import java.util.Arrays;

import static algorithm.utils.FormatConversion.strToIntArray;

/**
 * 719. Find K-th Smallest Pair Distance
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-08-23 16:03
 */
public class No719 {

    public static void main(String[] args) {
        // 0
        System.out.println(smallestDistancePair(strToIntArray("[1,3,1]"), 1));
        // 0
        System.out.println(smallestDistancePair(strToIntArray("[1,1,1]"), 2));
        // 5
        System.out.println(smallestDistancePair(strToIntArray("[1,6,1]"), 3));
    }

    /**
     * 思路：如果暴力找到所有数字对之间的距离，然后排序，复杂度会非常高，指数级别
     * 参考官方题解，这是一个可以使用二分查找+二分查找（或双指针）的问题
     * 首先对数组进行排序，这样所有的距离都会落入[0,最大值-最小值]的范围内，对距离进行二分left=0，right=最大值-最小值，mid=(left+right)/2
     * 然后，查找小于等于mid的距离有多少对，如果count>=k，则right=mid-1，否则，left=mid+1，当left>right时，停止，返回left即为所求的距离值
     * 对于查找"小于等于mid的距离有多少对"，可以有三种处理方式，但都需要对数字对的一端进行全遍历，下面以对右边数字进行遍历为例说明
     * 1）使用二分，对于某一个右边元素j，通过二分找到等于或大于mid-j的左边元素i，则j-i为当右边元素为j时，所有满足条件的数字对数
     * 2）使用双指针，因为右边元素j不断变大，所以左边元素也一定不断变大才能满足条件，因此滑动左边元素即可，同样也是j-i对
     * 3）结合二分与双指针，下一次左元素的起点不再从0开始，而是从上一次的左元素位置开始，这样可以减少二分查找的范围，进一步提升性能
     * 时间复杂度：n为数组长度，D为最大值-最小值
     * 1）O(nlog(n)*log(D))：外层log(D)，内层nlog(n)（遍历为n，二分为log(n)）
     * 2）O(n*(log(n)+log(D)))：排序复杂度nlog(n)，外层log(D)，内层n
     * 3）小于1和2
     */
    public static int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, left = 0, right = nums[n - 1] - nums[0];
        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = 0;

            int pre = 0;
            for (int j = 0; j < n; j++) {
                int i = binarySearch(nums, pre, j, nums[j] - mid);
                pre = i;
                cnt += j - i;
            }

            if (cnt >= k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static int binarySearch(int[] nums, int pre, int end, int target) {
        int left = pre, right = end;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

}