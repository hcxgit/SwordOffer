package leetcode;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author 三笠阿克曼
 * @date 2022/3/29
 * leetcode：20 括号匹配
 * 思路：栈
 */
public class IsValid {
    public boolean isValid(String s) {
        HashMap<Character, Character> hashMap = new HashMap<>();
        hashMap.put(']','[');
        hashMap.put(')','(');
        hashMap.put('}','{');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i <s.length(); i++) {
            char curr = s.charAt(i);
            if(!stack.isEmpty()&&stack.peek().equals(hashMap.get(curr))) {
                stack.pop();
            }else{
                stack.push(curr);
            }
        }
        if(stack.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    public static void main(String[] args) {
        IsValid case1 = new IsValid();
        System.out.println(case1.isValid("()"));
        System.out.println(case1.isValid("()[]{}"));
        System.out.println(case1.isValid("(]"));
    }
}
