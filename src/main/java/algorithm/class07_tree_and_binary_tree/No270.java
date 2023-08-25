package algorithm.class07_tree_and_binary_tree;

import algorithm.common.DataStructureDefinition.TreeNode;

import static algorithm.common.FormatConversion.strToTree;

/**
 * 270. Closest Binary Search Tree Value
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-08-03 11:08
 */
public class No270 {

    public static void main(String[] args) {
        // 3
        System.out.println((closestValue(strToTree("[4,2,5,1,3]"), 3.5)));
        // 4
        System.out.println((closestValue(strToTree("[1,null,8]"), 3.0)));
        // 4
        System.out.println((closestValue(strToTree("[4,2,5,1,3]"), 3.714286)));
        // 1
        System.out.println((closestValue(strToTree("[1]"), 4.428571)));
    }

    /**
     * 思路：BST搜索
     * 复杂度：log(n)
     */
    public static int closestValue(TreeNode root, double target) {
        int result = root.val;
        double diff = Math.abs(root.val - target);

        TreeNode curr = root;
        while (curr != null) {
            double abs = Math.abs(curr.val - target);
            if (abs < 1e-6) {
                // 如果和target相等，直接返回
                return curr.val;
            } else if (abs < diff) {
                // 如果小于当前差值，替换结果
                diff = abs;
                result = curr.val;
            } else if (Math.abs(abs - diff) < 1e-6 && curr.val < result) {
                // 如果等于当前差值，且数值较小，替换结果
                result = curr.val;
            }

            if (target < curr.val) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return result;
    }

}