"""
求平方根
num: 要求平方根的数
p: 精度
"""
# 方法一：二分查找
def sqrt1(num,p):  
    if num == 0:
        return 0
    left,right = 1, num
    while left < right:
        mid = (left+right)/2
        curr = mid**2
        if abs(curr-num) <p:
            return mid
        elif curr < num:
            left = mid
        else:
            right = mid

# 方法二：泰勒展开式
"""
问题可以等效与求解f(x)=x**2-num的零点
泰勒一阶展开：f(x)=f(x0)+f'(x0)(x-x0)
令f(x)=0,有x=x0-f(x0)/f'(x0)
对于f(x)，导数为2x，求得：x=1/2(x0 + num/x0)
每次按照上面的公式更新x即可
"""
def sqrt2(num,p):
    if num == 0:
        return 0
    x = num/2
    while abs(x**2-num)>p:
        x = (x+num/x)/2
    return x
if __name__ == "__main__":
    num,p = 16,1e-06
    print(sqrt2(num,p))
    