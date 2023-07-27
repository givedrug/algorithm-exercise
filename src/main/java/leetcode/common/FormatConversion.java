package leetcode.common;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import leetcode.common.DataStructureDefinition.ListNode;
import leetcode.common.DataStructureDefinition.TreeNode;

/**
 * 数据格式转换
 *
 * @create 2023-07-04 15:43
 */
public class FormatConversion {

    /**
     * 去掉前后中括号与空格
     *
     * @param str
     * @return
     */
    private static String commonRemove(String str) {
        str = StringUtils.removeStart(str, "[");
        str = StringUtils.removeEnd(str, "]");
        str = StringUtils.deleteWhitespace(str);
        return str;
    }

    /**
     * 字符串转一维int数组
     *
     * @param str
     * @return
     */
    public static int[] strToIntArray(String str) {
        String[] split = commonRemove(str).split(",");
        if (split.length == 0 || (split.length == 1 && Objects.equals(split[0], ""))) {
            return new int[]{};
        }
        return Arrays.stream(split).map(Integer::parseInt).mapToInt(Integer::intValue).toArray();
    }

    /**
     * 字符串转二维int数组
     *
     * @param str
     * @return
     */
    public static int[][] strToIntArrayArray(String str) {
        String[] split = commonRemove(str).split("\\],\\[");
        return Arrays.stream(split).map(FormatConversion::strToIntArray).toArray(int[][]::new);
    }

    /**
     * 字符串转一维char数组
     *
     * @param str
     * @return
     */
    public static char[] strToCharArray(String str) {
        str = commonRemove(str);
        str = StringUtils.remove(str, "\"");
        str = StringUtils.remove(str, ",");
        return str.toCharArray();
    }

    /**
     * 字符串转二维char数组
     *
     * @param str
     * @return
     */
    public static char[][] strToCharArrayArray(String str) {
        str = commonRemove(str);
        str = StringUtils.remove(str, "\"");
        String[] split = str.split("\\],\\[");
        return Arrays.stream(split).map(FormatConversion::strToCharArray).toArray(char[][]::new);
    }

    /**
     * 字符串转一维String数组
     *
     * @param str
     * @return
     */
    public static String[] strToStringArray(String str) {
        str = commonRemove(str);
        str = StringUtils.remove(str, "\"");
        return str.split(",");
    }

    /**
     * 字符串转List，返回head
     *
     * @param str
     * @return
     */
    public static ListNode strToListNode(String str) {
        int[] intArray = strToIntArray(str);
        ListNode head = null;
        ListNode current = null;
        for (int i = 0; i < intArray.length; i++) {
            if (Objects.isNull(head)) {
                head = new ListNode(intArray[i]);
                current = head;
            } else {
                current.next = new ListNode(intArray[i]);
                current = current.next;
            }
        }
        return head;
    }

    /**
     * 字符串转List，并在pos处成环，返回head
     *
     * @param str
     * @param pos
     * @return
     */
    public static ListNode strToListNodeWithCycle(String str, int pos) {
        ListNode head = strToListNode(str);
        if (pos >= 0) {
            ListNode posNode = head;
            while (pos > 0) {
                posNode = posNode.next;
                pos--;
            }
            ListNode tail = posNode;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = posNode;
        }
        return head;
    }

    /**
     * 字符串转List数组
     *
     * @param str
     * @return
     */
    public static ListNode[] strToListNodeArray(String str) {
        String[] split = commonRemove(str).split("\\],\\[");
        if (split.length == 0) {
            return new ListNode[0];
        }
        return Arrays.stream(split).map(FormatConversion::strToListNode).toArray(ListNode[]::new);
    }

    /**
     * 字符串转二叉树
     *
     * @param str
     * @return
     */
    public static TreeNode strToTree(String str) {
        List<TreeNode> treeNodes = Arrays.stream(commonRemove(str).split(",")).map(v -> {
            if ("null".equals(v)) {
                return null;
            } else {
                return new TreeNode(Integer.parseInt(v));
            }
        }).collect(Collectors.toList());
        int length = treeNodes.size();

        // 先将root入队列
        TreeNode root = treeNodes.get(0);
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.offer(root);

        int index = 0;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            // left
            if (++index < length) {
                TreeNode treeNode = treeNodes.get(index);
                if (treeNode != null) {
                    poll.left = treeNode;
                    queue.offer(treeNode);
                }
            } else {
                break;
            }
            // right
            if (++index < length) {
                TreeNode treeNode = treeNodes.get(index);
                if (treeNode != null) {
                    poll.right = treeNode;
                    queue.offer(treeNode);
                }
            } else {
                break;
            }
        }

        return root;
    }

}
