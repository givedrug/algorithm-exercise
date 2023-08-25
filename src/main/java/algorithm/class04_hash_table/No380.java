package algorithm.class04_hash_table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 380. Insert Delete GetRandom O(1)
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-28 16:02
 */
public class No380 {

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        // Inserts 1 to the set. Returns true as 1 was inserted successfully.
        System.out.println(randomizedSet.insert(1));
        // Returns false as 2 does not exist in the set.
        System.out.println(randomizedSet.remove(2));
        // Inserts 2 to the set, returns true. Set now contains [1,2].
        System.out.println(randomizedSet.insert(2));
        // getRandom() should return either 1 or 2 randomly.
        System.out.println(randomizedSet.getRandom());
        // Removes 1 from the set, returns true. Set now contains [2].
        System.out.println(randomizedSet.remove(1));
        // 2 was already in the set, so return false.
        System.out.println(randomizedSet.insert(2));
        // Since 2 is the only number in the set, getRandom() will always return 2.
        System.out.println(randomizedSet.getRandom());

        System.out.println();
    }

    /**
     * 思路：使用一个Map，一个ArrayList
     * 复杂度：1
     */
    public static class RandomizedSet {

        Map<Integer, Integer> map;

        List<Integer> list;

        Random r;

        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
            r = new Random();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            } else {
                map.put(val, list.size());
                list.add(val);
                return true;
            }
        }

        public boolean remove(int val) {
            if (map.containsKey(val)) {
                int index = map.get(val);
                // 注意这里不能用list.remove(index)，其时间复杂度不是1，需要先将末尾元素拷贝到index处，然后删除末尾元素，并修改map
                Integer tail = list.get(list.size() - 1);
                list.set(index, tail);
                list.remove(list.size() - 1);
                // 注意下面两个顺序，不能颠倒，否则删除末尾元素会有问题
                map.put(tail, index);
                map.remove(val);
                return true;
            } else {
                return false;
            }
        }

        public int getRandom() {
            return list.get(r.nextInt(list.size()));
        }

    }

}