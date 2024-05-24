package algorithm.class06_heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import static algorithm.utils.FormatConversion.strToIntArray;

/**
 * 347. Top K Frequent Elements
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2024-05-23 20:34
 */
public class No347 {

    @Test
    public void test() {
        int[] arr1 = topKFrequent(strToIntArray("[1,1,1,2,2,3]"), 2);
        Arrays.sort(arr1);
        Assert.assertArrayEquals(new int[]{1, 2}, arr1);
    }

    /**
     * 思路：先通过Map统计出每个数字出现的次数，然后通过对value从大到小排序维护一个大小为k的堆，依次存入元素，最后返回k的堆中元素即可
     * 当然还可以使用桶排序，每一个词频对应一个桶，这种方式时间复杂度为O(n)，但空间占用会更大一些
     * 时间复杂度：O(n*log(k))
     * 空间复杂度：O(n)
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(Map.Entry.comparingByValue());
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (maxHeap.size() < k) {
                maxHeap.offer(entry);
            } else {
                if (maxHeap.peek().getValue() < entry.getValue()) {
                    maxHeap.poll();
                    maxHeap.add(entry);
                }
            }
        }

        return maxHeap.stream().mapToInt(Map.Entry::getKey).toArray();
    }

    /**
     * 纯java stream方式
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent1(int[] nums, int k) {
        return Arrays.stream(nums)
            .boxed()
            .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
            .entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(k)
            .mapToInt(Map.Entry::getKey)
            .toArray();
    }

}