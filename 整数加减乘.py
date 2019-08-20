
# 给定一个合法的表达式字符串，只包含非负整数、加法、减法以及乘法符号（不会有括号），
# 例如7+3*4*5+2+4-3-1，

def cal(s):
    if '+' in s:
        res = 0
        for i in s.split('+'):
            res += cal(i)
        return res
    if '-' in s:
        temp = s.split('-')
        res = cal(temp[0])
        for i in temp[1:]:
            res -= cal(i)
        return res       
    if '*' in s:
        res = 1
        for i in s.split('*'):
            res *= int(i)
        return res
    return int(s)