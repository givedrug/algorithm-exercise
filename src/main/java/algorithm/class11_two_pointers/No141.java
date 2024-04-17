package algorithm.class11_two_pointers;

import algorithm.utils.DataStructureDefinition.ListNode;

import static algorithm.utils.FormatConversion.strToListNodeWithCycle;

/**
 * 141. Linked List Cycle
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-19 11:38
 */
public class No141 {

    public static void main(String[] args) {
        // true
        System.out.println((hasCycle(strToListNodeWithCycle("[3,2,0,-4]", 1))));
        // true
        System.out.println((hasCycle(strToListNodeWithCycle("[1,2]", 0))));
        // false
        System.out.println((hasCycle(strToListNodeWithCycle("[1]", -1))));
        // false
        System.out.println((hasCycle(strToListNodeWithCycle("[]", -1))));
    }

    /**
     * 思路：快慢指针，速度为1和2，如果快指针再次见到慢指针，说明有环存在
     * 复杂度：n
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            if (fast == slow || fast.next == slow) {
                return true;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }

}