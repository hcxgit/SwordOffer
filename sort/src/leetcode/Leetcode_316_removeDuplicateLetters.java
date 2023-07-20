package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 三笠阿克曼
 * @date 2023/7/20
 * @description Leetcode 316: 去除重复字母
 *
 *  1、单调栈
 *       - 栈里存放的字母递增（字典序）
 *       一、【currStr在栈内已经有了】，那么前面已经是字典序了，【跳过currStr】。
 *       二、【currStr栈中没有】，只要【栈top > currStr && 栈top后面还有】，则可【一直出栈】，最后【currStr入栈】。
 *          - 记录每个【字母的次数】，遍历到一次，则--，只要【次数 > 0】,说明后面还有，则可以移除。
 *          - 记录每个【字母在栈内是否存在】。
 *       注意：记得【更新curr次数、charInStack】
 *
 */
public class Leetcode_316_removeDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        /**
         1、单调栈（递增）
         */

        Deque<Character> stack = new ArrayDeque<>();
        int[] numOfChar = new int[26];  // 每个字母的个数
        int[] charInStack = new int[26]; // 字母是否在stack中

        // 初始化，统计每个字母出现的【次数】
        for(int i=0;i<s.length();i++){
            numOfChar[s.charAt(i) - 'a'] += 1;
        }

        char top;
        for(int i=0;i<s.length();i++){
            char curr = s.charAt(i);

            // 更新剩余次数
            numOfChar[curr - 'a'] -= 1;
            // 1、栈中已经有了curr, 跳过
            if(charInStack[curr - 'a'] == 1){
                continue;
            }

            // 2、栈中没有curr，只要【栈top > currStr && 栈top后面还有】，则可以【一直出栈】，最后【currStr入栈】
            while(!stack.isEmpty() && (top = stack.peek()) > curr && numOfChar[top - 'a'] > 0){
                stack.pop();
                charInStack[top - 'a'] = 0;
            }

            // curr入栈
            stack.push(curr);
            charInStack[curr - 'a'] = 1;
        }

        StringBuffer sb = new StringBuffer();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}
