package algorithm.class07_sorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import static algorithm.utils.FormatConversion.strToIntArray;
import static org.junit.Assert.assertArrayEquals;

/**
 * 1122. Relative Sort Array
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2024-04-12 15:54
 */
public class No1122 {

    @Test
    public void test() {
        assertArrayEquals(relativeSortArray(strToIntArray("[2,3,1,3,2,4,6,7,9,2,19]"), strToIntArray("[2,1,4,3,9,6]")),
            strToIntArray("[2,2,2,1,4,3,3,9,6,7,19]"));
        assertArrayEquals(relativeSortArray(strToIntArray("[28,6,22,8,44,17]"), strToIntArray("[22,28,8,6]")),
            strToIntArray("[22,28,8,6,17,44]"));
    }

    /**
     * 思路：遍历arr1，统计arr2中每个数字出现的次数，不在arr2中的数字加入一个list中，然后按要求输出到结果中
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // 统计arr2中每个数字出现的次数
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            countMap.put(arr2[i], 0);
        }
        // 不在arr2中的数字列表
        List<Integer> remainList = new ArrayList<>();

        // 处理arr1中的元素，如果在arr2中，则记录在countMap中；否则，记录在remainList中
        for (int i = 0; i < arr1.length; i++) {
            if (countMap.containsKey(arr1[i])) {
                countMap.put(arr1[i], countMap.get(arr1[i]) + 1);
            } else {
                remainList.add(arr1[i]);
            }
        }

        // 遍历arr2，将arr2中每个数字按照出现次数排序，然后依次将arr2中的数字放入result中
        int[] result = new int[arr1.length];
        int index = 0;
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < countMap.get(arr2[i]); j++) {
                result[index++] = arr2[i];
            }
        }

        // 将remainList中的数字按照升序排序，然后依次将remainList中的数字放入result中
        remainList.sort(Integer::compareTo);
        for (int i = 0; i < remainList.size(); i++) {
            result[index++] = remainList.get(i);
        }

        return result;
    }

}