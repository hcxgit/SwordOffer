#
# @lc app=leetcode.cn id=860 lang=python3
#
# [860] 柠檬水找零
#
class Solution:
    def lemonadeChange(self, bills) -> bool:
        if bills[0] != 5:
            return False
        curr={5:1,10:0} # 记录5的个数
        for i in bills[1:]:
            if i == 5: # 是5,5的个数加1
                curr[5] += 1
            elif i == 10: # 10则找5，
                curr[5] -= 1
                curr[10] += 1
            else:        # 20，则找15
                if curr[10] >0: # 有10，则 找零 10+5
                    curr[10] -= 1
                    curr[5] -= 1
                else:           # 没有10，找零3个5
                 curr[5] -= 3
            if curr[5] < 0:
                return False
        return True



