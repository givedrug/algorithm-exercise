package algorithm.class03_linked_list;

import algorithm.common.DataStructureDefinition.ListNode;

import static algorithm.common.FormatConversion.strToListNode;
import static algorithm.common.FormatOutput.printList;

/**
 * 82. Remove Duplicates from Sorted List II
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-14 23:08
 */
public class No82 {

    public static void main(String[] args) {
        // [1,2,5]
        printList(deleteDuplicates(strToListNode("[1,2,3,3,4,4,5]")));
        // [2,3]
        printList(deleteDuplicates(strToListNode("[1,1,1,2,3]")));
        // [1,2,3]
        printList(deleteDuplicates(strToListNode("[1,2,3,4,4,4]")));
        // []
        printList(deleteDuplicates(strToListNode("[1,1]")));
        // [1]
        printList(deleteDuplicates(strToListNode("[1]")));
        // []
        printList(deleteDuplicates(strToListNode("[]")));
    }

    /**
     * 思路：快慢指针+虚拟头结点
     * 复杂度：n
     */
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode left = dummyHead, right = head;
        while (right != null && right.next != null) {
            if (right.next.val == left.next.val) {
                while (right.next != null && right.next.val == left.next.val) {
                    right = right.next;
                }
                left.next = right.next;
            } else {
                left = left.next;
            }
            right = right.next;
        }
        return dummyHead.next;
    }

}