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
     * 字符串转一维int数组
     *
     * @param str
     * @return
     */
    public static int[] strToIntArray(String str) {
        str = StringUtils.removeStart(str, "[");
        str = StringUtils.removeEnd(str, "]");
        str = StringUtils.deleteWhitespace(str);
        String[] split = str.split(",");
        return Arrays.stream(split).map(Integer::parseInt).mapToInt(Integer::intValue).toArray();
    }

    /**
     * 字符串转二维int数组
     *
     * @param str
     * @return
     */
    public static int[][] strToIntArrayArray(String str) {
        str = StringUtils.removeStart(str, "[");
        str = StringUtils.removeEnd(str, "]");
        str = StringUtils.deleteWhitespace(str);
        String[] split = str.split("\\],\\[");
        return Arrays.stream(split).map(FormatConversion::strToIntArray).toArray(int[][]::new);
    }

    /**
     * 字符串转一维String数组
     *
     * @param str
     * @return
     */
    public static String[] strToStringArray(String str) {
        str = StringUtils.removeStart(str, "[");
        str = StringUtils.removeEnd(str, "]");
        str = StringUtils.deleteWhitespace(str);
        str = StringUtils.remove(str, "\"");
        return str.split(",");
    }

}
