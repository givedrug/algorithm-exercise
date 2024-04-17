package algorithm.class11_two_pointers;

import algorithm.utils.DataStructureDefinition.ListNode;

import static algorithm.utils.FormatConversion.strToListNode;
import static algorithm.utils.FormatOutput.printList;

/**
 * 876. Middle of the Linked List
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-22 13:05
 */
public class No876 {

    public static void main(String[] args) {
        // [3,4,5]
        printList((middleNode(strToListNode("[1,2,3,4,5]"))));
        // [4,5,6]
        printList((middleNode(strToListNode("[1,2,3,4,5,6]"))));
        // [2]
        printList((middleNode(strToListNode("[1,2]"))));
        // [1]
        printList((middleNode(strToListNode("[1]"))));
        // []
        printList((middleNode(strToListNode("[]"))));
    }

    /**
     * 思路：快慢指针
     * 复杂度：n
     */
    public static ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}