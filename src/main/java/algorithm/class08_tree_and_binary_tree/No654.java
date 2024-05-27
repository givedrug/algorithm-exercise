package algorithm.class08_tree_and_binary_tree;

import java.util.Arrays;

import org.junit.Test;

import algorithm.utils.DataStructureDefinition.TreeNode;

import static algorithm.utils.FormatOutput.printTree;

/**
 * 654. Maximum Binary Tree
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2024-05-27 16:07
 */
public class No654 {

    @Test
    public void test0() {
        int[] nums = {3, 2, 1, 6, 0, 5};
        TreeNode treeNode = constructMaximumBinaryTree(nums);
        // [6,3,5,null,2,0,null,null,1]
        printTree(treeNode);
    }

    @Test
    public void test1() {
        int[] nums = {3, 2, 1};
        TreeNode treeNode = constructMaximumBinaryTree(nums);
        // [3,null,2,null,1]
        printTree(treeNode);
    }

    /**
     * 思路：
     * 1、递归：在每次递归中，先找到当前数组中的最大值（因为反复的遍历查找最大值，所以复杂度是平方级），然后递归构建左右子树
     * 时间复杂度：O(n*n)
     * 空间复杂度：O(n)
     * <p>
     * 2、线段树：当时想维护一个二维数组，可以方便的查找某个区间内的最大值，但似乎生成这个二维数组的复杂度仍是平方级。
     * 后来了解到，对于维护任意区间信息，有一个方便的数据结构叫做线段树，构建复杂度是O(n)，查询某个区间信息复杂度是O(log(n))
     * 时间复杂度：O(n*log(n))
     * 空间复杂度：O(n)
     * <p>
     * 3、单调栈：单调栈常用于找到最近的比当前元素更大或更小的元素。显然，对于父节点来说，左子节点就是
     * 首先维护一个递减的栈；
     * 如果待插入的元素小于栈顶元素，直接入栈，栈顶元素.right=待插入元素；
     * 如果待插入的元素大于栈顶元素，栈顶元素出栈，待插入元素.left=栈顶元素，并继续尝试入栈。
     * 仅通过对数组的一次遍历，得到最终结果。
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length > 0) {
            int maxIndex = getMaxIndex(nums);
            int[] left = Arrays.copyOfRange(nums, 0, maxIndex);
            int[] right = Arrays.copyOfRange(nums, maxIndex + 1, nums.length);
            return new TreeNode(nums[maxIndex], constructMaximumBinaryTree(left), constructMaximumBinaryTree(right));
        }
        return null;
    }

    private int getMaxIndex(int[] nums) {
        int maxIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

}