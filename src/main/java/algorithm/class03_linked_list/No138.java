package algorithm.class03_linked_list;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 138. Copy List with Random Pointer
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-21 23:32
 */
public class No138 {

    public static void main(String[] args) {
        copyRandomList(strToNode("[[7,null],[13,0],[11,4],[10,2],[1,0]]"));
    }

    /**
     * 从字符串生成带random指针的链表
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

        // 先生成节点
        String[] split = str.split("],\\[");
        int length = split.length;
        Map<Integer, Node> map = new HashMap<>();
        Map<Integer, Integer> randomMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            String s = split[i];
            s = StringUtils.removeStart(s, "[");
            s = StringUtils.removeEnd(s, "]");
            s = StringUtils.deleteWhitespace(s);
            String[] sp = s.split(",");
            Integer val = Integer.parseInt(sp[0]);
            Integer index = "null".equals(sp[1]) ? null : Integer.parseInt(sp[1]);
            Node node = new Node(val);
            map.put(i, node);
            randomMap.put(i, index);
        }

        // 连接节点next与random
        for (int i = 0; i < length; i++) {
            map.get(i).next = i != length - 1 ? map.get(i + 1) : null;
            map.get(i).random = randomMap.get(i) != null ? map.get(randomMap.get(i)) : null;
        }

        return map.get(0);
    }

    /**
     * 思路：hash表，直接新旧映射
     * 另外，官方解答中，还有一种空间复杂度为1的方法：
     * 首先，将该链表中每一个节点拆分为两个相连的节点，例如对于链表A→B→C，我们可以将其变化为A→A′→B→B′→C→C′。对于任意一个原节点S，其拷贝节点S′即为其后继节点。
     * 然后，我们可以直接找到每一个拷贝节点S′的随机指针应当指向的节点，即为其原节点S的随机指针指向的节点T的后继节点T′。
     * 最后，再遍历一遍，将其拆分即可。
     * （需要注意原节点的随机指针可能为空，我们需要特别判断这种情况。）
     * <p>
     * 另外，最开始的时候，我采用的方法是oldMap为oldNode->index，newMap为index->newNode，然后遍历赋值
     * 但看了网友的解答，oldNode->index与index->newNode其实可以合并为oldNode->newNode，啊哈！
     * <p>
     * 时间复杂度：n
     * 空间复杂度：n
     */
    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node pos = head;
        Map<Node, Node> map = new HashMap<>();
        while (pos != null) {
            map.put(pos, new Node(pos.val));
            pos = pos.next;
        }

        pos = head;
        while (pos != null) {
            map.get(pos).next = map.get(pos.next);
            map.get(pos).random = map.get(pos.random);
            pos = pos.next;
        }

        return map.get(head);
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

}