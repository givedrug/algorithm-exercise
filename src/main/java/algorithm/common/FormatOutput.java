package algorithm.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

import algorithm.common.DataStructureDefinition.ListNode;
import algorithm.common.DataStructureDefinition.TreeNode;

/**
 * 格式化输出
 *
 * @create 2023-07-14 10:07
 */
public class FormatOutput {

    /**
     * 格式化输出List
     *
     * @param head
     */
    public static void printList(ListNode head) {
        if (head == null) {
            System.out.println("null");
        }

        List<Integer> valList = new ArrayList<>();
        while (Objects.nonNull(head)) {
            valList.add(head.val);
            head = head.next;
        }

        System.out.println(String.format("[%s]", valList.stream().map(String::valueOf).collect(Collectors.joining(","))));
    }

    /**
     * 格式化输出Tree
     *
     * @param root
     */
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("null");
        }

        List<Integer> valList = new ArrayList<>();
        valList.add(root.val);

        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            TreeNode left = treeNode.left;
            if (left != null) {
                valList.add(left.val);
                queue.offer(left);
            } else {
                valList.add(null);
            }
            TreeNode right = treeNode.right;
            if (right != null) {
                valList.add(right.val);
                queue.offer(right);
            } else {
                valList.add(null);
            }
        }
        System.out.println(String.format("[%s]", valList.stream().map(String::valueOf).collect(Collectors.joining(","))));
    }

    /**
     * 格式化输出TreeList
     *
     * @param treeList
     */
    public static void printTreeList(List<TreeNode> treeList) {
        treeList.forEach(FormatOutput::printTree);
        System.out.println();
    }

}
