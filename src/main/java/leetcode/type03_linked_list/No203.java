package leetcode.type03_linked_list;

import java.util.Objects;

import leetcode.common.DataStructureDefinition.ListNode;

import static leetcode.common.FormatConversion.strToListNode;
import static leetcode.common.FormatOutput.printListNode;

/**
 * 203. Remove Linked List Elements
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-14 09:48
 */
public class No203 {

    public static void main(String[] args) {
        // [1,2,3,4,5]
        printListNode(removeElements(strToListNode("[1,2,6,3,4,5,6]"), 6));
        // []
        printListNode(removeElements(strToListNode("[]"), 1));
        // []
        printListNode(removeElements(strToListNode("[7,7,7,7]"), 7));
        // [2]
        printListNode(removeElements(strToListNode("[1,2]"), 1));
    }

    /**
     * 方法一：
     * <p>
     * 思路：直接遍历
     * 另外，对于开头元素的处理，可以如下这样单独处理，也可以添加一个虚拟头（dummyNode）进行处理，返回虚拟头的next元素即可
     * 复杂度：n
     */
    public static ListNode removeElements(ListNode head, int val) {
        // 先删除开头等于val的节点
        if (Objects.isNull(head)) {
            return head;
        }
        ListNode prev = head;
        while (Objects.nonNull(prev) && prev.val == val) {
            ListNode tmp = prev;
            prev = prev.next;
            tmp.next = null;
        }

        // 顺序遍历删除等于val的节点
        head = prev;
        if (Objects.isNull(head)) {
            return head;
        }
        ListNode curr = prev.next;
        while (Objects.nonNull(curr)) {
            if (curr.val == val) {
                prev.next = curr.next;
                curr.next = null;
                curr = prev.next;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
        return head;
    }

    /**
     * 方法二：
     * <p>
     * 递归方式（来自官方题解）
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElementsFactorial(ListNode head, int val) {
        if (head == null)
            return null;
        head.next = removeElements(head.next, val);
        if (head.val == val) {
            return head.next;
        } else {
            return head;
        }
    }

}