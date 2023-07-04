package leetcode.common;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

/**
 * 测试数据格式转换
 *
 * @create 2023-07-04 15:43
 */
public class FormatConversion {

    /**
     * @param str
     * @return
     */
    public static int[] strToIntArray(String str) {
        str = StringUtils.removeStart(str, "[");
        str = StringUtils.removeEnd(str, "]");
        str = StringUtils.remove(str, " ");
        String[] split = str.split(",");
        return Arrays.stream(split).map(Integer::parseInt).mapToInt(Integer::intValue).toArray();
    }

}
