package algorithm.class05_binary_search;

import static algorithm.utils.FormatConversion.strToIntArray;

/**
 * 4. Median of Two Sorted Arrays
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-08-07 17:58
 */
public class No4 {

    public static void main(String[] args) {
        // 2.00
        System.out.println(findMedianSortedArrays(strToIntArray("[1,3]"), strToIntArray("[2]")));
        // 2.50
        System.out.println(findMedianSortedArrays(strToIntArray("[1,2]"), strToIntArray("[3,4]")));
    }

    /**
     * 思路：一个最简单的方法就是合并之后（也可以不合并，而在两个数组上分别使用一个指针并计数）找到中间位置，复杂度为m+n
     * 另外，官方解答中，提供一种log(m+n)的算法：
     * 根据中位数定义，如果m+n为奇数，中位数就是合并排序之后第(m+n)/2的元素，如果m+n为偶数，中位数就是第(m+n)/2和第(m+n)/2+1的平均数
     * 因此查找中位数的操作，就变成了找到第(m+n)/2或第(m+n)/2+1的数的过程，即找到第k小的数。
     * 维护两个指针，A与B数组，比较A与B数组第k/2的位置，则分为两种情况
     * 如果A的第k/2的数字小于B的第k/2的数字，则可以去掉A的这k/2个数，因为他们中一定没有第k小的数。反之去掉B的。然后更新k（减掉k/2）
     * 直到k为1或一个数组变空，就找到了中位数。
     * <p>
     * 另外，官方解答中，还提供一种log(min(m,n))的算法（详细证明请参考官方解答）：
     * 直接在较短的数组A上面二分查找，假设下标为i，并找到较长数组B的下表为j=(m+n+1)/2-i的数字，然后比较A[i-1]<=B[j]中i最大的位置，即为所得。
     * <p>
     * 复杂度：只提供log(m+n)的解法
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    public static int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

}