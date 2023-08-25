package algorithm.class03_linked_list;

import algorithm.common.DataStructureDefinition.ListNode;

import static algorithm.common.FormatConversion.strToListNode;
import static algorithm.common.FormatOutput.printList;

/**
 * 24. Swap Nodes in Pairs
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-23 11:57
 */
public class No24 {

    public static void main(String[] args) {
        // [2,1,4,3]
        printList(swapPairs(strToListNode("[1,2,3,4]")));
        // [2,1,4,3,5]
        printList(swapPairs(strToListNode("[1,2,3,4,5]")));
        // []
        printList(swapPairs(strToListNode("[]")));
        // [1]
        printList(swapPairs(strToListNode("[1]")));
    }

    /**
     * 思路：顺序遍历，两两交换
     * 复杂度：n
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pos = dummyHead;
        while (pos.next != null && pos.next.next != null) {
            ListNode first = pos.next;
            ListNode second = pos.next.next;
            ListNode third = pos.next.next.next;
            pos.next = second;
            second.next = first;
            first.next = third;
            pos = first;
        }
        return dummyHead.next;
    }

}