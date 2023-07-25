package leetcode.type04_hash_table;

/**
 * 706. Design HashMap
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-25 10:57
 */
public class No706 {

    public static void main(String[] args) {
        MyHashMap myHashMap = new No706().new MyHashMap();
        // The map is now [[1,1]]
        myHashMap.put(1, 1);
        // The map is now [[1,1], [2,2]]
        myHashMap.put(2, 2);
        // return 1, The map is now [[1,1], [2,2]]
        System.out.println(myHashMap.get(1));
        // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
        System.out.println(myHashMap.get(3));
        // The map is now [[1,1], [2,1]] (i.e., update the existing value)
        myHashMap.put(2, 1);
        // return 1, The map is now [[1,1], [2,1]]
        System.out.println(myHashMap.get(2));
        // remove the mapping for 2, The map is now [[1,1]]
        myHashMap.remove(2);
        // return -1 (i.e., not found), The map is now [[1,1]]
        System.out.println(myHashMap.get(2));

        return;
    }

    /**
     * 思路：与705相似
     * 复杂度：>1
     */
    class MyHashMap {

        private final int SIZE = 16;

        ListNode[] array;

        public MyHashMap() {
            array = new ListNode[16];
        }

        public void put(int key, int value) {
            int hash = getHash(key);
            ListNode newNode = new ListNode(key, value);
            if (array[hash] == null) {
                array[hash] = newNode;
            } else {
                ListNode pos = array[hash];
                while (pos != null) {
                    if (pos.key == key) {
                        pos.val = value;
                        return;
                    }
                    pos = pos.next;
                }
                newNode.next = array[hash];
                array[hash] = newNode;
            }
        }

        public int get(int key) {
            int hash = getHash(key);
            if (array[hash] != null) {
                ListNode pos = array[hash];
                while (pos != null) {
                    if (pos.key == key) {
                        return pos.val;
                    }
                    pos = pos.next;
                }
            }
            return -1;
        }

        public void remove(int key) {
            int hash = getHash(key);
            if (array[hash] != null) {
                ListNode pre = null;
                ListNode pos = array[hash];
                while (pos != null) {
                    ListNode next = pos.next;
                    if (pos.key == key) {
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

        private int getHash(int key) {
            return String.valueOf(key).hashCode() % SIZE;
        }

        class ListNode {
            public int key;
            public int val;
            public ListNode next;

            public ListNode() {
            }

            public ListNode(int key, int val) {
                this.key = key;
                this.val = val;
            }

        }

    }

}