package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author 三笠阿克曼
 * @date 2022/3/8
 * Leetcode 93 ：复原 IP 地址
 */
public class Leetcode_93_restoreIpAddresses {

    public List<String> restoreIpAddresses(String s) {

        Deque<String> path = new ArrayDeque<>();
        List<String> res = new ArrayList<>();
        //长度越界
        if(s.length() > 12 || s.length() < 4){
            return res;
        }
        dfs(0,0,s,path,res);
        return res;

    }

    public void dfs(int start, int pointNum, String s,  Deque<String> path, List<String> res){
        // 1、结束条件：4个点&&所有字符都用了，分割完毕
        if(pointNum == 4 && start >= s.length()){
            res.add(String.join(".", path));
            return;
        }

        // 2、剪枝：剩余字符过多或者过少
        int reLen = s.length()-start;
        if(reLen > 3*(4-pointNum) || reLen < 4-pointNum){
            return;
        }
        // 3、树分叉，选择
        for (int i=0; i<3; i++) {

            // 1)、处理当前节点(包括剪枝)
            if(start+i >= s.length()){  // 越界
                return;
            }
            int curr = judgeIp(start, start + i, s);  // 判断
            if( curr != -1){
                path.addLast(curr + "");
            }else{
                return;
            }
            // 2)、递归
            dfs(start+i+1, pointNum+1, s, path, res);
            // 3)、回溯，撤销处理结果
            path.removeLast();
        }
    }

    /**
     * 判断 字段[left,right]是否符合ip段
     * @return 转换后的IP段，-1表示不符合ip段
     */
    public int judgeIp(int left, int right, String s) {

        // 1、长度 > 3 || (长度>1 && 0开头)，直接返回-1
        if((right -left > 3) || right > left && s.charAt(left) == '0'){
            return -1;
        }
        // 转换成int型
        int res = 0;
        for (int i = left; i <= right; i++) {
            res = 10*res + (s.charAt(i)-'0');
        }
        // 2、> 255，返回-1
        if(res > 255){
            return -1;
        }
        return res;
    }

    public static void main(String[] args) {
        Leetcode_93_restoreIpAddresses case1 = new Leetcode_93_restoreIpAddresses();
        List<String> ipList = case1.restoreIpAddresses("25525511135");
        System.out.println(ipList);
    }
}
