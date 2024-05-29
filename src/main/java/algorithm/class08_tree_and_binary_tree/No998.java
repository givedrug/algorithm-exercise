package algorithm.class08_tree_and_binary_tree;

import org.junit.Test;

import algorithm.utils.DataStructureDefinition.TreeNode;

import static algorithm.utils.FormatConversion.strToTree;
import static algorithm.utils.FormatOutput.printTree;

/**
 * 998. Maximum Binary Tree II
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2024-05-29 14:27
 */
public class No998 {

    @Test
    public void test() {
        // [5,4,null,1,3,null,null,2]
        printTree(insertIntoMaxTree(strToTree("[4,1,3,null,null,2]"), 5));
        // [5,2,4,null,1,null,3]
        printTree(insertIntoMaxTree(strToTree("[5,2,4,null,1]"), 3));
        // [5,2,4,null,1,3]
        printTree(insertIntoMaxTree(strToTree("[5,2,3,null,1]"), 4));
    }

    /**
     * 思路：一个隐含的知识是，val位于数组的最右侧，所以只需要找树的右子树，找到插入位置即可，除非val是最大值，那么此时root节点作为val节点的左子树。
     * 如果val大于root，则新建节点，将root作为其左节点，返回val节点；
     * 如果val小于root，则递归遍历右子树，找到插入位置，插入val节点，返回root节点；
     * 其中，如果val一直小于右子树，则最终插入最右节点的右边；如果val大于某个右节点，则将其作为val节点的左子树，然后将val插入该节点的父节点的右侧；
     * <p>
     * 时间复杂度：平均O(log(n))，最坏O(n)，此时树退化为链表
     * 空间复杂度：O(1)
     */
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val < val) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }

        TreeNode fatherNode = findInsertPosition(root, val);
        if (fatherNode.right == null) {
            fatherNode.right = new TreeNode(val);
        } else {
            TreeNode node = new TreeNode(val);
            node.left = fatherNode.right;
            fatherNode.right = node;
        }

        return root;
    }

    /**
     * 递归找到右子树中的插入位置（返回其父节点）
     *
     * @param fatherNode
     * @param val
     * @return
     */
    private TreeNode findInsertPosition(TreeNode fatherNode, int val) {
        if (fatherNode.right == null || fatherNode.right.val < val) {
            return fatherNode;
        }
        return findInsertPosition(fatherNode.right, val);
    }

}