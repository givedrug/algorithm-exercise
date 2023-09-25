package algorithm.class04_hash_table;

import java.util.HashSet;
import java.util.Set;

import static algorithm.utils.FormatConversion.strToIntArray;

/**
 * 287. Find the Duplicate Number
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-08-07 10:46
 */
public class No287 {

    public static void main(String[] args) {
        // 2
        System.out.println(findDuplicate(strToIntArray("[1,3,4,2,2]")));
        // 3
        System.out.println(findDuplicate(strToIntArray("[3,1,3,4,2]")));
    }

    /**
     * 思路：使用HashSet
     * 另外，官方解答中提到了一种时间n空间1的方法，双指针法，首先将数组构造成链表（必然有环）index->nums[index]
     * 0 1 2 3 4
     * 1 3 4 2 2
     * 链表为：0->1->3->2->4(->2成环)
     * 简单证明为什么一定成环，首先0作为起点，只会指向别的节点，不会有节点指向0，
     * 这样（除去0）index就有n个，而被指向的节点共有n+1个，所以必然有一个节点至少被从两个位置指向（包括自指向）
     * 注意：不一定只有一个环，但从0出发的一定能找到有且一个环，如下例子
     * 0 1 2 3 4
     * 1 2 2 4 3
     * 链表为：0->1->2(->2自成环)
     * 3->4(->3成第二个环)
     * 这样构造出从0出发的一定有一个环的链表，且环入口就是重复元素，就可以使用快慢指针来找到了，
     * 而且这个链表并不一定要真正的构造出来，index和nums[index]的关系已经足够
     * <p>
     * 时间复杂度：n
     * 空间复杂度：n
     */
    public static int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            } else {
                set.add(num);
            }
        }
        return -1;
    }

}