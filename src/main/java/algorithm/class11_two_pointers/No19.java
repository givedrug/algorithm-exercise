package algorithm.class11_two_pointers;

import algorithm.utils.DataStructureDefinition.ListNode;

import static algorithm.utils.FormatConversion.strToListNode;
import static algorithm.utils.FormatOutput.printList;

/**
 * 19. Remove Nth Node From End of List
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-20 15:20
 */
public class No19 {

    public static void main(String[] args) {
        // [1,2,3,5]
        printList(removeNthFromEnd(strToListNode("[1,2,3,4,5]"), 2));
        // []
        printList(removeNthFromEnd(strToListNode("[1]"), 1));
        // [1]
        printList(removeNthFromEnd(strToListNode("[1,2]"), 1));
    }

    /**
     * 思路：快慢指针，快指针先走n步，然后同时遍历，直到快指针到达尾节点
     * 复杂度：n
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (n > 0 && fast != null) {
            fast = fast.next;
            n--;
        }

        // 如果list长度小于n，直接返回
        if (n > 0) {
            return head;
        }

        // 增加一个slow前一位置节点记录
        ListNode preSlow = new ListNode(-1, head);
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
            preSlow = preSlow.next;
        }
        if (slow == head) {
            // 如果删除头结点
            return head.next;
        } else {
            preSlow.next = slow.next;
            return head;
        }
    }

}