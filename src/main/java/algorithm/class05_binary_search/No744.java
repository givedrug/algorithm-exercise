package algorithm.class05_binary_search;

import static algorithm.utils.FormatConversion.strToCharArray;

/**
 * 744. Find Smallest Letter Greater Than Target
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-08-04 15:20
 */
public class No744 {

    public static void main(String[] args) {
        // c
        System.out.println(nextGreatestLetter(strToCharArray("[\"c\",\"f\",\"j\"]"), 'a'));
        // f
        System.out.println(nextGreatestLetter(strToCharArray("[\"c\",\"f\",\"j\"]"), 'c'));
        // x
        System.out.println(nextGreatestLetter(strToCharArray("[\"x\",\"x\",\"y\",\"y\"]"), 'z'));
    }

    /**
     * 思路：二分法
     * 复杂度：log(n)
     */
    public static char nextGreatestLetter(char[] letters, char target) {
        int left = 0, right = letters.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (letters[mid] == target + 1) {
                return letters[mid];
            }
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if (left != letters.length && letters[left] > target) {
            return letters[left];
        }

        return letters[0];
    }

}