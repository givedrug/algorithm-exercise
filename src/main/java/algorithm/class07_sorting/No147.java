package algorithm.class07_sorting;

import algorithm.utils.DataStructureDefinition.ListNode;

import static algorithm.utils.FormatConversion.strToListNode;
import static algorithm.utils.FormatOutput.printList;

/**
 * 147. Insertion Sort List
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2024-04-02 15:34
 */
public class No147 {

    public static void main(String[] args) {
        // [1,2,3,4]
        printList((insertionSortList(strToListNode("[4,2,1,3]"))));
        // [-1,0,3,4,5]
        printList((insertionSortList(strToListNode("[-1,5,3,4,0]"))));
    }

    /**
     * 思路：使用两层遍历，外层为逐个遍历插入的元素，内层为找到应该插入的位置
     * 复杂度：O(n^2)
     */
    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(Integer.MIN_VALUE, head);
        ListNode current = dummy;
        while (current.next != null) {
            // 当前要插入的元素为current.next
            int value = current.next.val;
            ListNode inner = dummy;
            while (inner != current.next && inner.next.val <= value) {
                inner = inner.next;
            }
            if (inner != current.next) {
                // 去掉当前要插入的元素current.next
                ListNode temp = current.next;
                current.next = temp.next;
                // 插入到inner的后面
                temp.next = inner.next;
                inner.next = temp;
            } else {
                current = current.next;
            }
        }
        return dummy.next;
    }

}