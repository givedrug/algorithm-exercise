package algorithm.class06_heap;

import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Test;

import static algorithm.utils.FormatConversion.strToIntArray;

/**
 * 703. Kth Largest Element in a Stream
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2024-05-23 20:05
 */
public class No703 {

    @Test
    public void test() {
        KthLargest kthLargest = new KthLargest(3, strToIntArray("[4, 5, 8, 2]"));
        Assert.assertEquals(4, kthLargest.add(3));   // return 4
        Assert.assertEquals(5, kthLargest.add(5));   // return 5
        Assert.assertEquals(5, kthLargest.add(10));  // return 5
        Assert.assertEquals(8, kthLargest.add(9));   // return 8
        Assert.assertEquals(8, kthLargest.add(4));   // return 8
    }

    /**
     * 思路：使用最小堆，大小为k，堆顶元素即为第k大的元素
     * 时间复杂度：O(n*log(k))
     * 空间复杂度：O(k)
     */
    class KthLargest {

        private final PriorityQueue<Integer> maxHeap = new PriorityQueue<>();

        private int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            if (maxHeap.size() < k) {
                maxHeap.add(val);
            } else {
                if (maxHeap.peek() < val) {
                    maxHeap.poll();
                    maxHeap.add(val);
                }
            }
            return maxHeap.peek();
        }
    }

}