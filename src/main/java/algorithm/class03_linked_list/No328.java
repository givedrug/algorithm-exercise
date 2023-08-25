package algorithm.class03_linked_list;

import algorithm.common.DataStructureDefinition.ListNode;

import static algorithm.common.FormatConversion.strToListNode;
import static algorithm.common.FormatOutput.printList;

/**
 * 328. Odd Even Linked List
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-23 11:13
 */
public class No328 {

    public static void main(String[] args) {
        // [1,3,5,2,4]
        printList(oddEvenList(strToListNode("[1,2,3,4,5]")));
        // [2,3,6,7,1,5,4]
        printList(oddEvenList(strToListNode("[2,1,3,5,6,4,7]")));
    }

    /**
     * 思路：维护两个list，一个奇数一个偶数，然后连接
     * 复杂度：n
     */
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode oddHead = null, evenHead = null, odd = null, even = null;
        ListNode pos = head;
        // 从1开始
        int index = 1;
        while (pos != null) {
            ListNode next = pos.next;
            if (index % 2 == 1) {
                if (oddHead == null) {
                    oddHead = pos;
                    odd = oddHead;
                } else {
                    odd.next = pos;
                    odd = odd.next;
                }
            } else {
                if (evenHead == null) {
                    evenHead = pos;
                    even = evenHead;
                } else {
                    even.next = pos;
                    even = even.next;
                }
            }
            pos.next = null;
            pos = next;
            index++;
        }

        // 连接
        odd.next = evenHead;
        return oddHead;
    }

}