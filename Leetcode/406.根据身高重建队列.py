#
# @lc app=leetcode.cn id=406 lang=python3
#
# [406] 根据身高重建队列
#
class Solution:
    def reconstructQueue(self, people):
        # 先对原数组按 h 降序、k 升序排序。然后遍历数组，根据元素的 k 值进行「插队操作」：直接把元素插入数组下标为 k 的位置。
        # k 值表示排在这个人前面且身高大于或等于 h 的人数，按 h 降序排序可以方便确定身高更高者的人数
        # 按 k 降序排序则先处理排在更前面的数，避免更多的元素交换操作

        people = sorted(people, key=lambda x: (-x[0], x[1]))
        result = []
        print(people)
        for each in people:
            result.insert(each[1], each)
        return result  
s = Solution().reconstructQueue([[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]])
