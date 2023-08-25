package algorithm.class06_sorting;

import java.util.Arrays;
import java.util.stream.Collectors;

import static algorithm.utils.FormatConversion.strToIntArray;

/**
 * 剑指 Offer 45. 把数组排成最小的数
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-08-25 14:28
 */
public class 剑指Offer45 {

    public static void main(String[] args) {
        // "3033459"
        System.out.println(minNumber(strToIntArray("[3,30,34,5,9]")));
        // "01012032233010301302333530535599"
        System.out.println(minNumber(strToIntArray("[2,203,23,3,33,301,302,3010,55,99,53,530,0,01,10]")));
        // "12112"
        System.out.println(minNumber(strToIntArray("[12,121]")));
    }

    /**
     * 思路：两个数字排序规则为
     * 1）依次比较每个位上的数字，如果某位数字不同，则小的在前，大的在后
     * 2）如果耗尽某个数字所有位数，则在其后补充该数字的第一位的数字，然后按规则1进行比较
     * 3）如果耗尽两个数字所有位数（按规则2补充后的），仍没有对比出大小，则返回(a + b)与(b + a)的字典序
     * <p>
     * 查看题解，发现自己想复杂了，其实任意两个数字，直接比较(a + b)与(b + a)的字典序即可，
     * 但我最开始的想法也有一点优势，就是占空间小一些，因为java字符串拼接会额外申请空间
     * <p>
     * 复杂度：n*log(n)
     */
    public static String minNumber(int[] nums) {
        return Arrays.stream(nums)
            .mapToObj(String::valueOf)
            .sorted((a, b) -> (a + b).compareTo(b + a))
            .collect(Collectors.joining(""));
    }

    /**
     * 原答案
     *
     * @param nums
     * @return
     */
    public static String minNumber1(int[] nums) {
        return Arrays.stream(nums)
            .mapToObj(String::valueOf)
            .sorted((a, b) -> {
                char[] charsA = a.toCharArray();
                char[] charsB = b.toCharArray();
                int max = Math.max(a.length(), b.length());
                int min = Math.min(a.length(), b.length());
                for (int i = 0; i < max; i++) {
                    if (i < min) {
                        if (charsA[i] != charsB[i]) {
                            return charsA[i] - charsB[i];
                        }
                    } else if (i >= a.length()) {
                        if (charsA[0] != charsB[i]) {
                            return charsA[0] - charsB[i];
                        }
                    } else {
                        if (charsA[i] != charsB[0]) {
                            return charsA[i] - charsB[0];
                        }
                    }
                }
                return (a + b).compareTo(b + a);
            })
            .collect(Collectors.joining(""));
    }

}