package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author 三笠阿克曼
 * @date 2023/6/25
 * @description Leetcode 739: 每日温度
 */
public class Leetcode_739_dailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {

        /**
         1、单调栈：存放元素的下标
         while(num > temperatures[栈顶元素])：
         出栈
         【下标差】就是两者之间的距离
         num 入栈
         */
        Deque<Integer> stack = new LinkedList<>();
        int[] res = new int[temperatures.length];
        stack.push(0);

        for(int i=1;i<temperatures.length;i++){
            int currNum = temperatures[i];
            while(!stack.isEmpty() && temperatures[stack.peekFirst()] < currNum){
                int first = stack.pop();
                res[first] = i - first;
            }

            stack.push(i);
        }
        return res;
    }
}
