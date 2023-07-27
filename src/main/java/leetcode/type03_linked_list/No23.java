package leetcode.type03_linked_list;

import java.util.Comparator;
import java.util.PriorityQueue;

import leetcode.common.DataStructureDefinition.ListNode;

import static leetcode.common.FormatConversion.strToListNodeArray;
import static leetcode.common.FormatOutput.printList;

/**
 * 23. Merge k Sorted Lists
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-24 14:41
 */
public class No23 {

    public static void main(String[] args) {
        // [1,1,2,3,4,4,5,6]
        printList(mergeKLists(strToListNodeArray("[[1,4,5],[1,3,4],[2,6]]")));
        // []
        printList(mergeKLists(strToListNodeArray("[[]]")));
        // []
        printList(mergeKLists(strToListNodeArray("[]")));
    }

    /**
     * 思路：使用小顶堆存储k个list的当前node，每次从小顶堆中取出最小值，放入新链表，如果这个最小值节点有next，再放入小顶堆，直到堆空
     * 另外，还可以使用分治法
     * 复杂度：n*log(k)
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 小顶堆，存取每个List当前节点
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(ln -> ln.val));
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.add(list);
            }
        }

        ListNode dummyHead = new ListNode(-1);
        ListNode pos = dummyHead;
        while (!minHeap.isEmpty()) {
            ListNode minValNode = minHeap.remove();
            pos.next = minValNode;
            pos = pos.next;
            ListNode next = minValNode.next;
            if (next != null) {
                minHeap.add(next);
            }
        }

        return dummyHead.next;
    }

}