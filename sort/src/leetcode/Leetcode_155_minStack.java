package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author 三笠阿克曼
 * @date 2022/5/8
 *
 * Leetcode 155：最小栈
 * 题目：设计一个支持 `push` ，`pop` ，`top` 操作，并能在**常数时间**内检索到**最小元素**的栈。
 * 思路：两个栈，一个存数据的stack，一个存最小值minStack。
 *      每次stack入栈一个数，minStack入栈当前最小值；
 *      出栈时，两个都出。
 */
public class Leetcode_155_minStack {
    private Deque<Integer> stack;
    private Deque<Integer> minStack;
    public Leetcode_155_minStack() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
    }

    public void push(int val) {
        stack.push(val);
        if(val <= getMin()){
            minStack.push(val);
        }else{
            minStack.push(getMin());
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.getFirst();
    }
}
