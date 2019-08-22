#
# @lc app=leetcode.cn id=96 lang=python3
#
# [96] 不同的二叉搜索树
#
class Solution:
    def numTrees(self, n: int) -> int:
        """
        1、G(n):长度为n的序列组成的不同二叉搜索树的个数
        2、F(i,n):以i为根节点的长度为n的序列组成的不同二叉搜索树的个数
        3、以i为根节点，左边为左子树，右边为右子树，所以G(n)=F(1,n)+F(2,n)...+F(n,n)
        4、F(i,n) = 左子树数量*右子树数量=G(i-1)*G(n-i)   G()只和长度有关，和序列内容无关
        5、所以,G(n) = 求和:G(i-1)*G(n-i),  G(0)=G(1) = 1
        """

        G = [0 for i in range(n+1)]
        G[0] = G[1] = 1
        for i in range(2,n+1):
            for j in range(1,i+1):
                G[i] += G[j-1]*G[i-j]
        return G[n]
        
