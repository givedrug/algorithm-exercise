package leetcode.type09_sliding_window;

import leetcode.common.DataStructureDefinition.ListNode;

import static leetcode.common.FormatConversion.strToListNode;
import static leetcode.common.FormatOutput.printList;

/**
 * 25. Reverse Nodes in k-Group
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-23 12:27
 */
public class No25 {

    public static void main(String[] args) {
        // [2,1,4,3,5]
        printList(reverseKGroup(strToListNode("[1,2,3,4,5]"), 2));
        // [3,2,1,4,5]
        printList(reverseKGroup(strToListNode("[1,2,3,4,5]"), 3));
    }

    /**
     * 思路：k个一组，翻转
     * 复杂度：n
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        // 虚拟头
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pos = dummyHead;
        while (pos != null) {
            ListNode start = pos;
            int count = 0;
            while (count < k && pos != null) {
                pos = pos.next;
                count++;
            }
            if (pos == null) {
                break;
            }
            if (count == k) {
                // 剩余长度足够k时
                ListNode next = pos.next;
                ListNode oldHead = start.next;
                pos.next = null;
                ListNode newHead = reverseList(oldHead);
                start.next = newHead;
                // oldHead翻转后变为尾节点
                oldHead.next = next;
                pos = oldHead;
            } else {
                //剩余成都不足k时，跳出
                break;
            }
        }
        return dummyHead.next;
    }

    /**
     * 206. Reverse Linked List
     *
     * @param head
     * @return
     */
    private static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode mid = slow.next;
        ListNode fast = mid.next;
        slow.next = null;
        while (fast != null) {
            mid.next = slow;
            slow = mid;
            mid = fast;
            fast = fast.next;
        }
        mid.next = slow;
        return mid;
    }

}