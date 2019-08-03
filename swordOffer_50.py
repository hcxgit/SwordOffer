# coding=utf-8

'''
Author: cxhao
Date: 2019/7/25 18:24
'''

# 字符流中第一次只出现一次的字符

# -*- coding:utf-8 -*-
class Solution:
    # 返回对应char
    def __init__(self):
        self.sets = {}
        self.chars = []
    def FirstAppearingOnce(self):
        while len(self.chars)>0 and self.sets[self.chars[0]] == '#':
            self.chars.pop(0)
        if len(self.chars) == 0:
            return '#'
        return self.chars[0]
    def Insert(self, char):
        if char not in self.sets.keys():
            self.sets[char] = 1
            self.chars.append(char)
        else:
            self.sets[char] = '#'


if __name__ == '__main__':
    s = Solution()
    s.Insert('h')
    print(s.FirstAppearingOnce())
    s.Insert('e')
    print(s.FirstAppearingOnce())
    s.Insert('l')
    print(s.FirstAppearingOnce())
    s.Insert('h')
    print(s.FirstAppearingOnce())
