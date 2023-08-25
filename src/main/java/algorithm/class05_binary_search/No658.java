package algorithm.class05_binary_search;

import java.util.ArrayList;
import java.util.List;

import static algorithm.common.FormatConversion.strToIntArray;

/**
 * 658. Find K Closest Elements
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-08-02 10:49
 */
public class No658 {

    public static void main(String[] args) {
        // [1]
        System.out.println(findClosestElements(strToIntArray("[1]"), 1, 1));
        // [2,3,4]
        System.out.println(findClosestElements(strToIntArray("[1,2,3,4,4,4,4,5,5]"), 3, 3));
        // [1,3]
        System.out.println(findClosestElements(strToIntArray("[0,0,0,1,3,5,6,7,8,8]"), 2, 2));
        // [8,10]
        System.out.println(findClosestElements(strToIntArray("[3,5,8,10]"), 2, 15));
        // [10]
        System.out.println(findClosestElements(strToIntArray("[1,1,1,10,10,10]"), 1, 9));
        // [1,2,3,4]
        System.out.println(findClosestElements(strToIntArray("[1,2,3,4,5]"), 4, 3));
        // [1,2,3,4]
        System.out.println(findClosestElements(strToIntArray("[1,2,3,4,5]"), 4, -1));
        // [1,4]
        System.out.println(findClosestElements(strToIntArray("[1,4,5]"), 2, 3));
    }

    /**
     * 思路：先找到与x最接近的数，然后找到k个最接近的数
     * 另外，参考官方解答，基本思路是一致的，但第一步找到边界的处理，想复杂了，根本不需要通过对比绝对值找到那个最接近x的值，
     * 而只需要找到大于等于x以及小于x的左右位置就可以了，因为最近接x的值不是左边就是右边
     * 复杂度：log(n)+k
     */
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int length = arr.length;
        int left = 0, right = length - 1, middle = left + (right - left) / 2;
        while (left <= right) {
            if (right == left) {
                break;
            }
            middle = left + (right - left) / 2;
            Turn turn = whichClosest(arr, left, middle, right, x);
            if (turn == Turn.Middle) {
                break;
            }
            if (right - left == 1) {
                int l = Math.abs(arr[left] - x);
                int r = Math.abs(arr[right] - x);
                middle = l == Math.min(l, r) ? left : right;
                break;
            }
            if (turn == Turn.Left) {
                right = middle;
            } else {
                left = middle;
            }
        }

        left = middle;
        right = middle;
        while (left != 0 && right != length - 1 && right - left + 1 < k) {
            int i = Math.abs(arr[left - 1] - x);
            int j = Math.abs(arr[right + 1] - x);
            if (i == Math.min(i, j)) {
                left--;
            } else {
                right++;
            }
        }
        if (left == 0) {
            return getListFromArray(arr, 0, k - 1);
        }
        if (right == length - 1) {
            return getListFromArray(arr, length - k, length - 1);
        }
        return getListFromArray(arr, left, right);
    }

    private static List<Integer> getListFromArray(int[] arr, int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    private static Turn whichClosest(int[] arr, int left, int middle, int right, int x) {
        if (middle == 0) {
            if (Math.abs(arr[0] - x) <= Math.abs(arr[1] - x)) {
                return Turn.Middle;
            } else {
                return Turn.Right;
            }
        }
        if (middle == arr.length - 1) {
            if (Math.abs(arr[arr.length - 1] - x) <= Math.abs(arr[arr.length - 2] - x)) {
                return Turn.Middle;
            } else {
                return Turn.Left;
            }
        }
        int m = Math.abs(arr[middle] - x);
        int ml = Math.abs(arr[middle - 1] - x);
        int mr = Math.abs(arr[middle + 1] - x);
        int minm = Math.min(m, Math.min(ml, mr));
        if (m == minm) {
            if (x == arr[middle]) {
                return Turn.Middle;
            }
            if (x < arr[middle]) {
                return Turn.Left;
            } else {
                return Turn.Right;
            }
        }
        if (ml == minm) {
            return Turn.Left;
        } else {
            return Turn.Right;
        }
    }

    enum Turn {
        Middle, Left, Right;
    }

    /**
     * 官方解答
     *
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElements1(int[] arr, int k, int x) {
        int right = binarySearch(arr, x);
        int left = right - 1;
        while (k-- > 0) {
            if (left < 0) {
                right++;
            } else if (right >= arr.length) {
                left--;
            } else if (x - arr[left] <= arr[right] - x) {
                left--;
            } else {
                right++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = left + 1; i < right; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

    public int binarySearch(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= x) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

}