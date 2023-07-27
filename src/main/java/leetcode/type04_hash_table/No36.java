package leetcode.type04_hash_table;

import java.util.HashSet;
import java.util.Set;

import static leetcode.common.FormatConversion.strToCharArrayArray;

/**
 * 36. Valid Sudoku
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-27 10:19
 */
public class No36 {

    public static void main(String[] args) {
        // true
        System.out.println(isValidSudoku(strToCharArrayArray(
            "[[\"5\",\"3\",\".\",\".\",\"7\",\".\",\".\",\".\",\".\"]\n" +
                ",[\"6\",\".\",\".\",\"1\",\"9\",\"5\",\".\",\".\",\".\"]\n" +
                ",[\".\",\"9\",\"8\",\".\",\".\",\".\",\".\",\"6\",\".\"]\n" +
                ",[\"8\",\".\",\".\",\".\",\"6\",\".\",\".\",\".\",\"3\"]\n" +
                ",[\"4\",\".\",\".\",\"8\",\".\",\"3\",\".\",\".\",\"1\"]\n" +
                ",[\"7\",\".\",\".\",\".\",\"2\",\".\",\".\",\".\",\"6\"]\n" +
                ",[\".\",\"6\",\".\",\".\",\".\",\".\",\"2\",\"8\",\".\"]\n" +
                ",[\".\",\".\",\".\",\"4\",\"1\",\"9\",\".\",\".\",\"5\"]\n" +
                ",[\".\",\".\",\".\",\".\",\"8\",\".\",\".\",\"7\",\"9\"]]")));

        // false
        System.out.println(isValidSudoku(strToCharArrayArray(
            "[[\"8\",\"3\",\".\",\".\",\"7\",\".\",\".\",\".\",\".\"]\n" +
                ",[\"6\",\".\",\".\",\"1\",\"9\",\"5\",\".\",\".\",\".\"]\n" +
                ",[\".\",\"9\",\"8\",\".\",\".\",\".\",\".\",\"6\",\".\"]\n" +
                ",[\"8\",\".\",\".\",\".\",\"6\",\".\",\".\",\".\",\"3\"]\n" +
                ",[\"4\",\".\",\".\",\"8\",\".\",\"3\",\".\",\".\",\"1\"]\n" +
                ",[\"7\",\".\",\".\",\".\",\"2\",\".\",\".\",\".\",\"6\"]\n" +
                ",[\".\",\"6\",\".\",\".\",\".\",\".\",\"2\",\"8\",\".\"]\n" +
                ",[\".\",\".\",\".\",\"4\",\"1\",\"9\",\".\",\".\",\"5\"]\n" +
                ",[\".\",\".\",\".\",\".\",\"8\",\".\",\".\",\"7\",\"9\"]]")));

    }

    /**
     * 思路：分别按行、按列、按九宫遍历
     * 复杂度：1（遍历次数为常数）
     */
    public static boolean isValidSudoku(char[][] board) {
        // 按行
        for (int i = 0; i < 9; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];
                if (!dealOneChar(ch, set)) {
                    return false;
                }
            }
        }

        // 按列
        for (int j = 0; j < 9; j++) {
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < 9; i++) {
                char ch = board[i][j];
                if (!dealOneChar(ch, set)) {
                    return false;
                }
            }
        }

        // 按九宫（m标识行倍数，n标识列倍数）
        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++) {
                Set<Character> set = new HashSet<>();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        char ch = board[m * 3 + i][n * 3 + j];
                        if (!dealOneChar(ch, set)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * 处理一个字符，如果set中存在，则false，否则加入set
     *
     * @param ch
     * @param set
     * @return
     */
    private static boolean dealOneChar(char ch, Set<Character> set) {
        if (ch != '.') {
            if (set.contains(ch)) {
                return false;
            } else {
                set.add(ch);
            }
        }
        return true;
    }

}