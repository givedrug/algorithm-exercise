package algorithm.class04_hash_table;

import java.util.LinkedList;

/**
 * 705. Design HashSet
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-24 15:42
 */
public class No705 {

    public static void main(String[] args) {
        MyHashSet myHashSet = new No705().new MyHashSet();
        // set = [1]
        myHashSet.add(1);
        // set = [1, 2]
        myHashSet.add(2);
        // return True
        System.out.println(myHashSet.contains(1));
        // return False, (not found)
        System.out.println(myHashSet.contains(3));
        // set = [1, 2]
        myHashSet.add(2);
        // return True
        System.out.println(myHashSet.contains(2));
        // set = [1]
        myHashSet.remove(2);
        // return False, (already removed)
        System.out.println(myHashSet.contains(2));

        return;
    }

    /**
     * 思路：使用一个数组存数据，数组元素是一个链表表头，该链表下所有key的hash值相等
     * 另外，这里只是给出一个最简单的hashSet实现，如果要进行优化，可以从下面几点考虑：
     * 1）hash函数，可以采用更优的散列函数
     * 2）链表改为树，参考Java内置HashMap的实现
     * 3）扩容机制，当元素个数过多时，可以对其进行扩容，降低hash冲突概率
     * 复杂度：>1
     */
    class MyHashSet0 {

        private final int SIZE = 16;
        ListNode[] array;

        public MyHashSet0() {
            array = new ListNode[16];
        }

        public void add(int key) {
            int hash = getHash(key);
            ListNode newNode = new ListNode(key);
            if (array[hash] == null) {
                array[hash] = newNode;
            } else {
                ListNode pos = array[hash];
                while (pos != null) {
                    if (pos.val == key) {
                        return;
                    }
                    pos = pos.next;
                }
                newNode.next = array[hash];
                array[hash] = newNode;
            }
        }

        public void remove(int key) {
            int hash = getHash(key);
            if (array[hash] != null) {
                ListNode pre = null;
                ListNode pos = array[hash];
                while (pos != null) {
                    ListNode next = pos.next;
                    if (pos.val == key) {
                        if (pre == null) {
                            array[hash] = next;
                        } else {
                            pre.next = next;
                        }
                        pos.next = null;
                        return;
                    } else {
                        pre = pos;
                        pos = next;
                    }
                }
            }
        }

        public boolean contains(int key) {
            int hash = getHash(key);
            if (array[hash] != null) {
                ListNode pos = array[hash];
                while (pos != null) {
                    if (pos.val == key) {
                        return true;
                    }
                    pos = pos.next;
                }
            }
            return false;
        }

        private int getHash(int key) {
            return String.valueOf(key).hashCode() % SIZE;
        }

        class ListNode {
            public int val;
            public ListNode next;

            public ListNode() {
            }

            public ListNode(int val) {
                this.val = val;
            }

            public ListNode(int val, ListNode next) {
                this.val = val;
                this.next = next;
            }
        }

    }


    class MyHashSet {
        private static final int BASE = 769;
        private LinkedList<Integer>[] data;

        /**
         * 初始化
         */
        public MyHashSet() {
            data = new LinkedList[BASE];
            for (int i = 0; i < BASE; ++i) {
                data[i] = new LinkedList<>();
            }
        }

        public void add(int key) {
            int h = hash(key);
            for (Integer element : data[h]) {
                if (element == key) {
                    return;
                }
            }
            data[h].offerLast(key);
        }

        public void remove(int key) {
            int h = hash(key);
            for (Integer element : data[h]) {
                if (element == key) {
                    data[h].remove(element);
                    return;
                }
            }
        }

        /**
         * 如果存在key，则返回true
         */
        public boolean contains(int key) {
            int h = hash(key);
            for (Integer element : data[h]) {
                if (element == key) {
                    return true;
                }
            }
            return false;
        }

        private int hash(int key) {
            return key % BASE;
        }
    }
}