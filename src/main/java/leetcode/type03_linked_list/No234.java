package leetcode.type03_linked_list;

import leetcode.common.DataStructureDefinition.ListNode;

import static leetcode.common.FormatConversion.strToListNode;

/**
 * 234. Palindrome Linked List
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-14 14:14
 */
public class No234 {

    public static void main(String[] args) {
        // false
        System.out.println(isPalindrome(strToListNode("[1,2,6,3,4,5,6]")));
        // ture
        System.out.println(isPalindrome(strToListNode("[1,2,2,1]")));
        // false
        System.out.println(isPalindrome(strToListNode("[1,2]")));
        // true
        System.out.println(isPalindrome(strToListNode("[1,1,1]")));
        // true
        System.out.println(isPalindrome(strToListNode("[1,1]")));
        // true
        System.out.println(isPalindrome(strToListNode("[1]")));
        // true
        System.out.println(isPalindrome(strToListNode("[]")));
    }

    /**
     * 思路：
     * 首先，使用慢指针（1次移动1节点），快指针（1次移动2节点）遍历找到中间节点
     * 然后，从中间节点到尾节点的部分进行翻转
     * 最后，对比两个链表，是否相同
     * <p>
     * 时间复杂度：n
     * 空间复杂度：1
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head, anotherHead;
        while (true) {
            if (fast.next == null) {
                anotherHead = reverseList(slow.next, fast);
                slow.next.next = slow;
                slow.next = null;
                break;
            } else if (fast.next.next == null) {
                fast = fast.next;
                anotherHead = reverseList(slow.next, fast);
                slow.next = null;
                break;
            } else {
                slow = slow.next;
                fast = fast.next.next;
            }
        }

        while (true) {
            if (head.val != anotherHead.val) {
                return false;
            }
            head = head.next;
            anotherHead = anotherHead.next;
            if (head == anotherHead) {
                return true;
            }
        }
    }

    /**
     * 翻转链表，返回翻转后head
     * 即将A->B->C->null翻转为null<-A<-B<-C
     *
     * @param start
     * @param end
     * @return
     */
    private static ListNode reverseList(ListNode start, ListNode end) {
        if (start == end) {
            return end;
        }
        ListNode prev = start;
        ListNode curr = start.next;
        ListNode next = curr.next;
        prev.next = null;
        while (curr != end) {
            curr.next = prev;
            prev = curr;
            curr = next;
            next = next.next;
        }
        curr.next = prev;
        return end;
    }

}