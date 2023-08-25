package algorithm.class01_array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import static algorithm.utils.FormatConversion.strToIntArrayArray;

/**
 * 56. Merge Intervals
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-05 14:13
 */
public class No56 {

    public static void main(String[] args) {
        // [[1, 6], [8, 10], [15, 18]]
        System.out.println(Arrays.deepToString(merge(strToIntArrayArray("[[1,3],[2,6],[8,10],[15,18]]"))));
        // [[1, 5]]
        System.out.println(Arrays.deepToString(merge(strToIntArrayArray("[[1,4],[4,5]]"))));
    }

    /**
     * 思路：先排序，然后两两合并
     * 复杂度：n
     */
    public static int[][] merge(int[][] intervals) {
        // 排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        // 合并
        for (int i = 1; i < intervals.length; i++) {
            int[] left = intervals[i - 1];
            int[] right = intervals[i];
            // 判断是否重合
            if (right[0] <= left[1]) {
                intervals[i][0] = Math.min(left[0], right[0]);
                intervals[i][1] = Math.max(left[1], right[1]);
                // left置为null
                intervals[i - 1] = null;
            }
        }

        // 清理null值
        return Arrays.stream(intervals).filter(Objects::nonNull).toArray(int[][]::new);
    }

}