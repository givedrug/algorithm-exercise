package algorithm.class07_sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static algorithm.utils.FormatConversion.strToIntArray;

/**
 * 506. Relative Ranks
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2024-04-08 10:47
 */
public class No506 {

    public static void main(String[] args) {
        // ["Gold Medal","Silver Medal","Bronze Medal","4","5"]
        System.out.println(Arrays.toString(findRelativeRanks(strToIntArray("[5,4,3,2,1]"))));
        // ["Gold Medal","5","Bronze Medal","Silver Medal","4"]
        System.out.println(Arrays.toString(findRelativeRanks(strToIntArray("[10,3,8,9,4]"))));
    }

    /**
     * 思路：数字转为对象后排序并更新rank信息，对象排序后可以保留引用关系，直接遍历原列表根据rank值输出结果字符串数组
     * 或者先排序，然后通过map保存rank映射（通过查找排名复杂度为O(n)），注意不要直接在排序后列表中查找排名，那样复杂度会升高为O(n^2)。
     * 复杂度：O(n*log(n))
     */
    public static String[] findRelativeRanks(int[] score) {
        int len = score.length;
        // 转为对象
        List<ItemAndRank> itemAndRankList = new ArrayList<>();
        for (int sc : score) {
            itemAndRankList.add(new ItemAndRank(sc, -1));
        }
        // 根据值排序，并更新rank字段
        List<ItemAndRank> copiedList = new ArrayList<>(itemAndRankList);
        Collections.sort(copiedList);
        for (int i = 0; i < len; i++) {
            copiedList.get(i).rank = i + 1;
        }
        // 输出结果字符串数组
        String[] result = new String[len];
        for (int i = 0; i < len; i++) {
            int rank = itemAndRankList.get(i).rank;
            switch (rank) {
                case 1:
                    result[i] = "Gold Medal";
                    break;
                case 2:
                    result[i] = "Silver Medal";
                    break;
                case 3:
                    result[i] = "Bronze Medal";
                    break;
                default:
                    result[i] = String.valueOf(rank);
            }
        }
        return result;
    }

    private static class ItemAndRank implements Comparable<ItemAndRank> {
        int item;
        int rank;

        public ItemAndRank(int item, int rank) {
            this.item = item;
            this.rank = rank;
        }

        @Override
        public int compareTo(ItemAndRank other) {
            // 从大到小排序
            return Integer.compare(other.item, this.item);
        }
    }

}