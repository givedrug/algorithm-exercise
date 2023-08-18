package leetcode.type04_hash_table;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 460. LFU Cache
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-17 15:04
 */
public class No460 {

    public static void main(String[] args) {
        No460 no460 = new No460();
        LFUCache lfu = no460.new LFUCache(2);
        // cache=[1,_], cnt(1)=1
        lfu.put(1, 1);
        // cache=[2,1], cnt(2)=1, cnt(1)=1
        lfu.put(2, 2);
        // return 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        System.out.println(lfu.get(1));
        // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        lfu.put(3, 3);
        // return -1 (not found)
        System.out.println(lfu.get(2));
        // return 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        System.out.println(lfu.get(3));
        // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        lfu.put(4, 4);
        // return -1 (not found)
        System.out.println(lfu.get(1));
        // return 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        System.out.println(lfu.get(3));
        // return 4
        // cache=[4,3], cnt(4)=2, cnt(3)=3
        System.out.println(lfu.get(4));
    }

    /**
     * 思路：map-list-map方案
     * 首先建立一个hashmap，key是访问次数，value是一个linkedList，list内所有节点的访问次数是一样的，并按访问时间排序
     * 然后再建立一个hashmap，key是输入数据的key，value是上述list的节点，为了方便定位某个具体key-value数据
     * 最后再维护一个当前最小访问次数的变量，用户快速找到需要剔除的元素
     * 复杂度：1
     */
    class LFUCache {

        Map<Integer, List<Node>> freqMap = new HashMap<>();

        Map<Integer, Node> dataMap = new HashMap<>();

        int max;

        int lowestFreq = 0;

        public LFUCache(int capacity) {
            this.max = capacity;
        }

        /**
         * 如果key不存在：返回-1
         * 如果key存在：找到key的freq，以及所在list，从list中删除，并加入freq+1的list头部（更新node的freq），返回value
         *
         * @param key
         * @return
         */
        public int get(int key) {
            if (!dataMap.containsKey(key)) {
                return -1;
            } else {
                Node node = removeAndIncrease(key);
                return node.val;
            }
        }

        /**
         * 如果key不存在：
         * 如果LFU未满，则直接加入freqMap，key==1的list的head处（并加入dataMap）
         * 如果LFU已满，删除freqMap，key==lowestFreq的list的tail处节点（并删除dataMap），并加入freqMap，key==1的list的head处（并加入dataMap）
         * 重置lowestFreq=1
         * 如果key存在：
         * 找到key的freq，以及所在list，从list中删除，并加入freq+1的list头部（更新node的value、freq）
         * 如果当前freq==lowestFreq，且删除后list为空，则lowestFreq增加1
         *
         * @param key
         * @param value
         */
        public void put(int key, int value) {
            if (!dataMap.containsKey(key)) {
                Node node = new Node(key, value);
                if (dataMap.size() != max) {
                    freqMap.computeIfAbsent(1, k -> new LinkedList<>()).add(0, node);
                } else {
                    List<Node> nodeList = freqMap.get(lowestFreq);
                    int lastIdx = nodeList.size() - 1;
                    Node lastNode = nodeList.get(lastIdx);
                    dataMap.remove(lastNode.key);
                    nodeList.remove(lastIdx);
                    freqMap.get(1).add(0, node);
                }
                dataMap.put(key, node);
                lowestFreq = 1;
            } else {
                Node node = removeAndIncrease(key);
                node.val = value;
            }
        }

        /**
         * 移动当前元素到frea+1的list
         *
         * @param key
         * @return
         */
        private Node removeAndIncrease(int key) {
            Node node = dataMap.get(key);
            int freq = node.freq;
            List<Node> nodeList = freqMap.get(freq);
            nodeList.remove(node);
            if (node.freq == lowestFreq && nodeList.isEmpty()) {
                lowestFreq++;
            }
            freqMap.computeIfAbsent(freq + 1, k -> new LinkedList<>()).add(0, node);
            node.freq++;
            return node;
        }

        class Node {
            int key;
            int val;
            int freq = 1;

            Node() {
            }

            Node(int key, int val) {
                this.key = key;
                this.val = val;
            }

        }
    }

}