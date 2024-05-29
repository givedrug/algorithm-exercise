package algorithm.class08_tree_and_binary_tree;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import algorithm.utils.DataStructureDefinition.TreeNode;

import static algorithm.utils.FormatConversion.strToIntArray;
import static algorithm.utils.FormatOutput.printTree;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2024-05-29 15:18
 */
public class No105 {

    @Test
    public void test() {
        // [3,9,20,null,null,15,7]
        printTree(buildTree(strToIntArray("[3,9,20,15,7]"), strToIntArray("[9,3,15,20,7]")));
        // [-1]
        printTree(buildTree(strToIntArray("[-1]"), strToIntArray("[-1]")));
    }

    public Map<Integer, Integer> elementIndexMap;

    /**
     * 思路：每次遍历时，先序数组的第一个元素，即为root元素
     * 通过在中序数组中找到root元素的索引，中序数组root元素的左侧即为左子树，右侧即为右子树，分别对其进行递归即可得到最终树
     * 备注：因为没有重复元素，因此可以直接构造map存储元素与index的映射关系，O(1)时间内找到索引。如果有重复元素，则map的value需要存储数组，平均复杂度略微上升
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> elementIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            elementIndexMap.put(inorder[i], i);
        }
        this.elementIndexMap = elementIndexMap;
        return getTreeNode(preorder, 0, preorder.length, inorder, 0, preorder.length);
    }

    /**
     * 递归
     *
     * @param preorder
     * @param pl
     * @param pr
     * @param inorder
     * @param il
     * @param ir
     * @return
     */
    private TreeNode getTreeNode(int[] preorder, int pl, int pr, int[] inorder, int il, int ir) {
        if (pr - pl == 0) {
            return null;
        }
        if (pr - pl == 1) {
            return new TreeNode(preorder[pl]);
        }
        int rootValue = preorder[pl];
        int rootIndex = elementIndexMap.get(rootValue);
        TreeNode leftNode = getTreeNode(preorder, pl + 1, pl + 1 + rootIndex - il, inorder, il, rootIndex);
        TreeNode rightNode = getTreeNode(preorder, pl + 1 + rootIndex - il, pr, inorder, rootIndex + 1, ir);
        return new TreeNode(rootValue, leftNode, rightNode);
    }

}