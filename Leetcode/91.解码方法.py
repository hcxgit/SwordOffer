#
# @lc app=leetcode.cn id=91 lang=python3
#
# [91] 解码方法
#
class Solution:
    def numDecodings(self, s: str) -> int:
        # dp[i]: 以 i为结尾的编码方式的总数
        # dp[i] = dp[i+1] + (1 if ....)  因为0只能和前面的数组和，所以从后往前遍历
        if s=='':
            return 1 
        if s[0] == '0':
            return 0   
        if 1 <= int(s) < 10 or s=='20':
            return 1
        if 10< int(s) <= 26:
            return 2
        s1,s2 = 0,0
        if 0<int(s[:1])<=26:
            s1 = self.numDecodings(s[1:])
        if 0<int(s[:2])<=26:
            s2 = self.numDecodings(s[2:])
        return s1+s2
# if __name__ == "__main__":
    # print(Solution().numDecodings('12120'))
