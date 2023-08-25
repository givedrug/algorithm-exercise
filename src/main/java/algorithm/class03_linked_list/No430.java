package algorithm.class03_linked_list;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * 430. Flatten a Multilevel Doubly Linked List
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-20 17:33
 */
public class No430 {

    public static void main(String[] args) {
        // [1,2,3,7,8,11,12,9,10,4,5,6]
        printListNode(flatten(strToNode("[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]")));
        // [1,3,2]
        printListNode(flatten(strToNode("[1,2,null,3]")));
        // []
        printListNode(flatten(strToNode("[]")));
    }

    private static void printListNode(Node head) {
        List<Integer> valList = new ArrayList<>();
        while (Objects.nonNull(head)) {
            valList.add(head.val);
            head = head.next;
        }
        System.out.println(String.format("[%s]", valList.stream().map(String::valueOf).collect(Collectors.joining(","))));
    }


    /**
     * 从字符串生成多级链表
     *
     * @param str
     * @return
     */
    private static Node strToNode(String str) {
        // 格式化
        str = StringUtils.removeStart(str, "[");
        str = StringUtils.removeEnd(str, "]");
        str = StringUtils.deleteWhitespace(str);
        if ("".equals(str)) {
            return null;
        }

        // 分组
        List<List<String>> group = new ArrayList<>();
        List<String> newGroup = null;
        String[] split = (str + ",null").split(",");
        for (int i = 0; i < split.length; i++) {
            if (newGroup == null) {
                newGroup = new ArrayList<>();
            }
            if (!"null".equals(split[i])) {
                // 如果是数字，直接加到当前group
                newGroup.add(split[i]);
            } else {
                // 如果是null，分情况
                if (!"null".equals(split[i - 1])) {
                    // 如果前一个是数字，则它是结尾null，需要加入分组中，并另起一个group
                    group.add(newGroup);
                    newGroup = null;
                } else {
                    // 如果前一个是null，则它是开头null，直接加入即可
                    newGroup.add(split[i]);
                }
            }
        }

        // 生成
        Node head = null;
        Node preHead = null;
        Node currHead = null;
        for (int i = 0; i < group.size(); i++) {
            List<String> list = group.get(i);
            int count = 0;
            Node curr = null;
            for (int j = 0; j < list.size(); j++) {
                if ("null".equals(list.get(j))) {
                    count++;
                } else {
                    if (currHead == null) {
                        currHead = new Node(Integer.parseInt(list.get(j)));
                        curr = currHead;
                    } else {
                        Node node = new Node(Integer.parseInt(list.get(j)));
                        curr.next = node;
                        node.prev = curr;
                        curr = node;
                    }
                }
            }
            if (preHead != null) {
                // 其他level，绑定child
                Node tmp = preHead;
                while (count > 0) {
                    tmp = tmp.next;
                    count--;
                }
                tmp.child = currHead;
            } else {
                // 第一个level
                head = currHead;
            }
            preHead = currHead;
            currHead = null;
        }

        return head;
    }

    /**
     * 思路：此题本质是在在树上遍历（prev相当于指向父节点，child相当于左孩子，next相当于右孩子，将所有节点合并进右侧），可采用深度优先算法
     * 从head开始遍历，当前层级中间节点（非尾节点）带child时，递归处理，当前层级尾节点带child时，将其摆直后继续遍历
     * 时间复杂度：n
     * 空间复杂度：d（层数）
     */
    public static Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        doFlatten(head);
        return head;
    }

    private static Node doFlatten(Node head) {
        Node pos = head;
        while (pos.next != null || pos.child != null) {
            // 因为java逻辑判断短路规则，pos.next == null与pos.child != null一定同时成立
            if (pos.next == null) {
                // 当前层级尾节点带child时，将其摆直后继续遍历
                Node childHead = pos.child;
                pos.next = childHead;
                childHead.prev = pos;
                pos.child = null;
                pos = pos.next;
            } else {
                if (pos.child != null) {
                    // 当前层级中间节点（非尾节点）带child时，递归
                    Node posNext = pos.next;
                    Node childHead = pos.child;
                    Node childTail = doFlatten(childHead);
                    pos.child = null;
                    pos.next = childHead;
                    childHead.prev = pos;
                    childTail.next = posNext;
                    posNext.prev = childTail;
                    pos = posNext;
                } else {
                    pos = pos.next;
                }
            }
        }
        return pos;
    }

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        Node(int val) {
            this.val = val;
        }
    }

}