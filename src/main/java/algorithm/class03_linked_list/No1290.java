package algorithm.class03_linked_list;

import algorithm.utils.DataStructureDefinition.ListNode;

import static algorithm.utils.FormatConversion.strToListNode;

/**
 * 1290. Convert Binary Number in a Linked List to Integer
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-22 12:57
 */
public class No1290 {

    public static void main(String[] args) {
        // 5
        System.out.println((getDecimalValue(strToListNode("[1,0,1]"))));
        // 0
        System.out.println((getDecimalValue(strToListNode("[0]"))));
    }

    /**
     * 思路：转换为字符串，然后转进制
     * 另外，官方解答中可以一边遍历一遍计算，只需让原来的数字乘2加上现在的数字即可
     * 复杂度：n
     */
    public static int getDecimalValue(ListNode head) {
        if (head == null) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            head = head.next;
        }
        return Integer.valueOf(sb.toString(), 2);
    }

}