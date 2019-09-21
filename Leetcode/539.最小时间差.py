#
# @lc app=leetcode.cn id=539 lang=python3
#
# [539] 最小时间差
#
class Solution:
    def findMinDifference(self, timePoints):
        timePoints = [list(map(int,time.split(':'))) for time in timePoints]
        timePoints.sort(key=lambda x: (x[0],x[1]))
        res = 24*60
        for i in range(1,len(timePoints)):
            t2 = timePoints[i]
            t1 = timePoints[i-1]
            time = (t2[0]-t1[0])*60+t2[1]-t1[1]
            res = min(res,time)
        # 最后一个和第一个
        last = (timePoints[0][0]+24-timePoints[-1][0])*60+(timePoints[0][1]-timePoints[-1][1])
        return min(res,last)

