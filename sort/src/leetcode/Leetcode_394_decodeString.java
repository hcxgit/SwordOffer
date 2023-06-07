package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 三笠阿克曼
 * @date 2023/6/7
 * @description Leetcode 394: 字符串解码
 *
 *   char != "]":
 *      进栈
 *  否则:
 *      出栈，解析出 num * [] 的字符串内容，再入栈
 *
 */
public class Leetcode_394_decodeString {
    public String decodeString(String s) {

        Deque<Character> dq = new ArrayDeque<>();

        int len = s.length();
        StringBuffer res = new StringBuffer();
        for(int i=0;i<len;i++){
            // 入栈
            if(s.charAt(i) != ']'){
                dq.offerLast(s.charAt(i));
            }else{
                // 遇到']', 出栈, 解析出 num * [] 的字符串内容，再入栈
                StringBuffer currStr = new StringBuffer(); // 存当前[]里的字符

                // 1、出栈，直到'['
                while(dq.peekLast() != '['){
                    currStr.insert(0,dq.pollLast());
                }
                // 2、'['出栈
                dq.pollLast();
                // 3、 解析数字
                StringBuffer num = new StringBuffer();
                while(!dq.isEmpty() && isNum(dq.peekLast())){
                    num.insert(0,dq.pollLast());
                }
                // 4、拼num * []
                StringBuffer chars = new StringBuffer();
                for(int j=0;j < Integer.valueOf(num.toString());j++){
                    chars.append(currStr);
                }
                // 5、入栈
                String strs = chars.toString();
                for(int j=0;j<strs.length();j++){
                    dq.offerLast(strs.charAt(j));
                }
            }
        }

        while(!dq.isEmpty()){
            res.append(dq.pollFirst());
        }
        return res.toString();
    }
    public boolean isChar(char c){
        return c >= 'A' && c <= 'z';
    }

    public boolean isNum(char c){
        return c >= '0' && c <= '9';
    }
}
