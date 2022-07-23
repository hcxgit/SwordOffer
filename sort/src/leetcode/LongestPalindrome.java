package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/7/22
 *
 * Leetcode 5：最长回文子串
 *
 *  1、标准dp
 *     如果s[i:j]是回文串，且 len>2 ,那么s[i+1:j-1]也是回文串，并且 s[i]=s[j]
 *
 *     dp[i][j] 表示s[i：j] 是否是回文串
 *
 *     综上，
 *          1、如果s[i] != s[j],  则 dp[i][j] = 0;
 *          2、如果s[i] == s[j]
 *              2.1  如果去掉首尾的子串长度 len(s[i+1:j-1]) < 2,则 dp[i][j] = 1;
 *              2.2  否则，dp[i][j] = dp[i+1][j-1]
 *     注意：遍历时先确定回文子串尾部
 *
 *  2、中心扩展算法
 *      我们枚举所有的【回文中心】（长度分为1和2）并往【两边扩展】。
 *      expand(s, i, i);  1个点扩展，奇数
 *      expand(s, i, i+1); 2个点扩展，偶数
 */
public class LongestPalindrome {
    //1、动态规划
    public String longestPalindrome2(String s) {
        int len = s.length();
        //长度为 1
        if (len == 1){
            return s;
        }

        int[][] dp = new int[len][len];
        int longest = 1,start = 0;

        //初始化
        for (int i = 0; i < len; i++){
            dp[i][i] = 1;
        }

        // 状态转移
        // 注意：遍历时先确定回文子串尾部
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++){
                // 头尾字符不相等，不是回文串
                if(s.charAt(i) != s.charAt(j)){
                    dp[i][j] = 0;
                }else{
                    // 头尾去掉剩余字符长度 <2,肯定是回文串
                    if(j-i < 3){
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = dp[i+1][j-1];
                    }
                }

                // 更新
                if(dp[i][j] == 1 && longest < j-i+1){
                    longest = j-i+1;
                    start = i;
                }
            }
        }
        return s.substring(start,start + longest);
    }

    // 2、中心扩展法
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1){
            return "";
        }

        int len = s.length();
        int start=0, end=0;
        for (int i = 0; i <len ; i++) {
            int odd = expand(s, i, i); // 奇数
            int even = expand(s, i, i + 1); // 偶数
            int maxLen = Math.max(odd,even);
            // 更新
            if(maxLen > end-start+1){
                start = i - (maxLen-1)/2;
                end = i + maxLen/2;
            }
        }
        return s.substring(start, end+1);
    }

    //扩展的回文串长度
    public int expand(String s,int left,int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        // 回文串的长度: right-left+1-2 = right-left-1
        return right-left-1;
    }


    public static void main(String[] args) {
        String s = "babad";
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        String s1 = longestPalindrome.longestPalindrome(s);
        System.out.println(s1);
    }
}
