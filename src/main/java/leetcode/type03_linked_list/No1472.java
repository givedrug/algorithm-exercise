package leetcode.type03_linked_list;

import java.util.LinkedList;
import java.util.List;

/**
 * 1472. Design Browser History
 *
 * @author <a href="https://github.com/givedrug">givedrug</a>
 * @create 2023-07-19 10:31
 */
public class No1472 {

    public static void main(String[] args) {
        BrowserHistory browserHistory = new No1472().new BrowserHistory("leetcode.com");
        // You are in "leetcode.com". Visit "google.com"
        browserHistory.visit("google.com");
        // You are in "google.com". Visit "facebook.com"
        browserHistory.visit("facebook.com");
        // You are in "facebook.com". Visit "youtube.com"
        browserHistory.visit("youtube.com");
        // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
        browserHistory.back(1);
        // You are in "facebook.com", move back to "google.com" return "google.com"
        browserHistory.back(1);
        // You are in "google.com", move forward to "facebook.com" return "facebook.com"
        browserHistory.forward(1);
        // You are in "facebook.com". Visit "linkedin.com"
        browserHistory.visit("linkedin.com");
        // You are in "linkedin.com", you cannot move forward any steps.
        browserHistory.forward(2);
        // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
        browserHistory.back(2);
        // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"
        browserHistory.back(7);
    }

    /**
     * 思路：链表（还可以通过hash表实现key是index，value是url）
     * 备注：题目中有一点没有说清楚，就是当前index如果在中间时，插入url后，url会覆盖index+1的位置，并且大于index+1的位置也都作废不能访问
     * 复杂度：？
     */
    class BrowserHistory {

        List<String> history = new LinkedList<>();

        int currentIndex, max;

        public BrowserHistory(String homepage) {
            history.add(homepage);
            currentIndex = 0;
        }

        public void visit(String url) {
            if (currentIndex == history.size() - 1) {
                history.add(++currentIndex, url);
            } else {
                history.set(++currentIndex, url);
            }
            max = currentIndex;
        }

        public String back(int steps) {
            currentIndex -= steps;
            currentIndex = Math.max(currentIndex, 0);
            return history.get(currentIndex);
        }

        public String forward(int steps) {
            currentIndex += steps;
            currentIndex = Math.min(currentIndex, max);
            return history.get(currentIndex);
        }
    }

}