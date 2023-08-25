package algorithm.class05_binary_search;

import static algorithm.utils.FormatConversion.strToIntArray;

/**
 * 702. Search in a Sorted Array of Unknown Size
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-08-03 13:38
 */
public class No702 {

    public static void main(String[] args) {
        // 4
        System.out.println(search(new ArrayReaderImpl(strToIntArray("[-1,0,3,5,9,12]")), 9));
        // -1
        System.out.println(search(new ArrayReaderImpl(strToIntArray("[-1,0,3,5,9,12]")), 2));
    }

    /**
     * 思路：先通过幂函数2^n确定粗略边界，然后二分查找
     * 复杂度：log(n)
     */
    public static int search(ArrayReader reader, int target) {
        if (reader.get(0) == Integer.MAX_VALUE) {
            return -1;
        }

        int boundary = 1;
        while (true) {
            if (reader.get(boundary) != Integer.MAX_VALUE) {
                boundary *= 2;
            } else {
                break;
            }
        }

        int left = 0, right = boundary;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (reader.get(mid) == target) {
                return mid;
            }
            if (reader.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


    interface ArrayReader {
        int get(int index);
    }

    static class ArrayReaderImpl implements ArrayReader {

        public int[] array;

        public ArrayReaderImpl(int[] array) {
            this.array = array;
        }

        @Override
        public int get(int index) {
            if (index >= 0 && index <= array.length - 1) {
                return array[index];
            }
            return Integer.MAX_VALUE;
        }
    }

}