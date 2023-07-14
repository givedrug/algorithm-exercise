package leetcode.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import leetcode.common.DataStructureDefinition.ListNode;

/**
 * 格式化输出
 *
 * @create 2023-07-14 10:07
 */
public class FormatOutput {

    /**
     * 格式化输出ListNode
     *
     * @param head
     */
    public static void printListNode(ListNode head) {
        List<Integer> valList = new ArrayList<>();
        while (Objects.nonNull(head)) {
            valList.add(head.val);
            head = head.next;
        }
        System.out.println(String.format("[%s]", valList.stream().map(String::valueOf).collect(Collectors.joining(","))));
    }

}
