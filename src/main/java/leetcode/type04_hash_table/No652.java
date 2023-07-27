package leetcode.type04_hash_table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import leetcode.common.DataStructureDefinition.TreeNode;

import static leetcode.common.FormatConversion.strToTree;
import static leetcode.common.FormatOutput.printTreeList;

/**
 * 652. Find Duplicate Subtrees
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-27 10:56
 */
public class No652 {

    public static void main(String[] args) {
        // [[2,4],[4]]
        printTreeList(Objects.requireNonNull(findDuplicateSubtrees(strToTree("[1,2,3,4,null,2,4,null,null,4]"))));
        // [[1]]
        printTreeList(Objects.requireNonNull(findDuplicateSubtrees(strToTree("[2,1,1]"))));
        // [[2,3],[3]]
        printTreeList(Objects.requireNonNull(findDuplicateSubtrees(strToTree("[2,2,2,3,null,3,null]"))));
    }

    /**
     * 思路：递归记录所有子树到map（key为先序遍历顺序生成的字符串），遇到重复的key则记录到重复节点上
     * 另外，官方解答中，提到一个优化方法，注意到，父节点生成的str包含了子节点的str，所以，可以用数字替换这些固定字符串（每一个新的子树，赋予一个新的序号）
     * 复杂度：n
     */
    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, TreeNode> allSubtrees = new HashMap<>();
        Set<TreeNode> duplicateSubtrees = new HashSet<>();
        doFind(root, allSubtrees, duplicateSubtrees);
        return new ArrayList<>(duplicateSubtrees);
    }

    /**
     * 记录所有distinct的子树（通过字符串区分），同时查找重复子树（返回第一次进入map的节点）
     *
     * @param node
     * @param allSubtrees
     * @param duplicateSubtrees
     * @return
     */
    private static String doFind(TreeNode node, Map<String, TreeNode> allSubtrees, Set<TreeNode> duplicateSubtrees) {
        if (node == null) {
            return "null";
        }

        // 叶子节点，忽略左右子节点的null值（也可以不忽略，把这段去掉，只不过str最后都会多一些null）
        if (node.left == null && node.right == null) {
            String str = String.valueOf(node.val);
            check(node, str, allSubtrees, duplicateSubtrees);
            return str;
        }

        // 非叶子节点，连接本节点与左子树、右子树
        String join = String.join(",",
            String.valueOf(node.val),
            doFind(node.left, allSubtrees, duplicateSubtrees),
            doFind(node.right, allSubtrees, duplicateSubtrees));
        check(node, join, allSubtrees, duplicateSubtrees);
        return join;
    }

    /**
     * 检查是否重复
     *
     * @param node
     * @param str
     * @param allSubtrees
     * @param duplicateSubtrees
     */
    private static void check(TreeNode node, String str, Map<String, TreeNode> allSubtrees, Set<TreeNode> duplicateSubtrees) {
        if (allSubtrees.containsKey(str)) {
            duplicateSubtrees.add(allSubtrees.get(str));
        } else {
            allSubtrees.put(str, node);
        }
    }

}