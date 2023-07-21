package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author 三笠阿克曼
 * @date 2023/7/21
 * @description Leetcode 402: 移掉K位数字
 *
 *        1、单调栈
 *           - 1、stack.peek() > curr && k > 0, 则一直出栈
 *           - 2、k > 0 && 栈不空，说明栈中都是有序，【从栈顶移出剩余的k个数】
 *           - 3、去除前导0
 *           - 4、最终栈为空，则直接返回
 *           - 5、拼接字符串
 */
public class Leetcode_402_removeKdigits {
    public String removeKdigits(String num, int k) {
         // 1、单调栈
        if(num.length() == k){
            return "0";
        }

        Deque<Character> stack = new LinkedList<>();
        for(int i=0;i<num.length();i++){
            char curr = num.charAt(i);

            while(!stack.isEmpty() && stack.peek() > curr && k >0){
                stack.pop();
                k--;
            }
            stack.push(curr);
        }

        // 从栈顶移除剩余的k个数
        while(k > 0 && !stack.isEmpty()){
            stack.pop();
            k--;
        }

        // 去除前导0
        while(!stack.isEmpty() && stack.peekLast() == '0'){
            stack.removeLast();
        }

        // 去除完前导0后，栈如果空，则直接返回
        if(stack.isEmpty()){
            return "0";
        }

        StringBuffer sb = new StringBuffer();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}
