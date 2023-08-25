package algorithm.class03_linked_list;

import algorithm.utils.DataStructureDefinition.ListNode;

import static algorithm.utils.FormatConversion.strToListNodeWithCycle;

/**
 * 142. Linked List Cycle II
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-19 23:24
 */
public class No142 {

    public static void main(String[] args) {
        // node at 1-index
        System.out.println((detectCycle(strToListNodeWithCycle("[3,2,0,-4]", 1))));
        // node at 0-index
        System.out.println((detectCycle(strToListNodeWithCycle("[1,2]", 0))));
        // null
        System.out.println((detectCycle(strToListNodeWithCycle("[1]", -1))));
        // null
        System.out.println((detectCycle(strToListNodeWithCycle("[]", -1))));
    }

    /**
     * 思路：第一次遍历，先通过快慢指针找到环，然后在环内遍历出环的大小len
     * 第二次遍历，再用间隔为len的两个指针重新遍历，相遇出即为环入口
     * 另外，官方解答中，还有一种更为简练的方法，当快慢指针相遇时，再引入第三个指针，然后同时移动快指针/慢指针与第三个指针，它们将在环入口相遇（可数学证明）
     * 时间复杂度：n
     * 空间复杂度：1
     */
    public static ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        // 第一次遍历：找环长度
        ListNode slow = head;
        ListNode fast = head.next;
        int len = 0;
        while (fast != null && fast.next != null) {
            if (fast == slow || fast.next == slow) {
                ListNode flag = fast;
                while (fast.next != flag) {
                    len++;
                    fast = fast.next;
                }
                len++;
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        if (len == 0) {
            return null;
        }

        // 第二次遍历：找环入口
        slow = head;
        fast = head;
        while (len > 0) {
            fast = fast.next;
            len--;
        }
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

}