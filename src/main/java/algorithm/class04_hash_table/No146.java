package algorithm.class04_hash_table;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU Cache
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-17 10:46
 */
public class No146 {

    public static void main(String[] args) {
        No146 no146 = new No146();
        LRUCache cache = no146.new LRUCache(2);
        cache.put(2, 1);
        cache.put(3, 2);
        // 2
        System.out.println(cache.get(3));
        // 1
        System.out.println(cache.get(2));
        cache.put(4, 3);
        // 1
        System.out.println(cache.get(2));
        // -1
        System.out.println(cache.get(3));
        // 3
        System.out.println(cache.get(4));

    }

    /**
     * 思路：通过双向链表（目的是保存先后顺序）表示LRU，但查询复杂度为n，所以增加一个辅助hash表，使得查询复杂度降为1
     * 另外，还可以借助java提供的LinkedHashMap来实现，它本身就是通过list+hash结合实现的
     * 复杂度：1
     */
    class LRUCache {

        Node head;

        Node tail;

        Map<Integer, Node> map = new HashMap<>();

        // 最大容量
        int max;

        public LRUCache(int capacity) {
            this.max = capacity;
            // 增加虚拟头和虚拟尾
            Node dummyHead = new Node();
            Node dummyTail = new Node();
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
            head = dummyHead;
            tail = dummyTail;
        }

        // GET操作：
        // LRU中不存在key，返回-1；
        // LRU中存在key，需要移动到头部，并返回value
        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            } else {
                Node node = map.get(key);
                if (head.next != node) {
                    insert(head, head.next, remove(node));
                }
                return node.val;
            }
        }

        // PUT操作：
        // LRU中不存在key，在头部插入（同时插入map），如果LRU已满，还需移除最末尾元素（同时删除map）；
        // LRU中存在key，需要移动到头部，并更新value
        public void put(int key, int value) {
            if (!map.containsKey(key)) {
                Node newNode = new Node(key, value);
                map.put(key, newNode);
                insert(head, head.next, newNode);
                if (map.size() > max) {
                    map.remove(tail.prev.key);
                    remove(tail.prev);
                }
            } else {
                Node node = map.get(key);
                node.val = value;
                if (head.next != node) {
                    insert(head, head.next, remove(node));
                }
            }
        }

        private Node remove(Node node) {
            Node left = node.prev;
            Node right = node.next;
            left.next = right;
            right.prev = left;
            node.prev = null;
            node.next = null;
            return node;
        }

        private void insert(Node left, Node right, Node newNode) {
            left.next = newNode;
            newNode.next = right;
            right.prev = newNode;
            newNode.prev = left;
        }

        class Node {
            int key;
            int val;
            Node prev = null;
            Node next = null;

            Node() {
            }

            Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
    }

}