package algorithm.class03_linked_list;

import algorithm.utils.DataStructureDefinition.ListNode;

import static algorithm.utils.FormatConversion.strToListNode;
import static algorithm.utils.FormatOutput.printList;

/**
 * 206. Reverse Linked List
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-20 15:37
 */
public class No206 {

    public static void main(String[] args) {
        // [5,4,3,2,1]
        printList(reverseList(strToListNode("[1,2,3,4,5]")));
        // [2,1]
        printList(reverseList(strToListNode("[1,2]")));
        // []
        printList(reverseList(strToListNode("[]")));
    }

    /**
     * 思路：快中慢三指针
     * 另外，还可以遍历当前指针，并用头插法插入新list
     * 另外，官方解答中还有一种递归方式，虽然空间复杂度为n，但形式简单：
     * ListNode prev = null;
     * ListNode curr = head;
     * while (curr != null) {
     * ListNode next = curr.next;
     * curr.next = prev;
     * prev = curr;
     * curr = next;
     * }
     * return prev;
     * <p>
     * 时间复杂度：n
     * 空间复杂度：1
     */
    public static ListNode reverseList(ListNode head) {
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