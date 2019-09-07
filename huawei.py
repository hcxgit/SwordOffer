# 1、大小端 
# 9个字符一组，每组的第一个字符是标志位，后面8个字符是地址。如果标志位是0，地址逆序，标志位是1地址不变。
def bm(s):
    res = []
    for i in range(0,len(s),9):
        if s[i] == '0':
            res.append(s[i+1:i+9][::-1])
        else:
            res.append(s[i+1:i+9])
    return res

# 2、TSP 问题
    """
    d[i][j]：到第i个节点时，走了j包含的城市集合的最短路径
    j: 二进制每一位表示一个城市是否被访问过 1001:{1,4}
    """
def TSP(c):
    n = len(c)
    d=[[0xffffffff for j in range(1<<n)] for i in range(n)]  
    d[0][1] = 0
    for i in range(1,n):
        d[i][0] = c[i][0]  # 从i出发回到i花费0

    # 计算j包含那些顶点,利用与运算
    def find_vertex(j):
        vertexs=set()
        for v in range(n):
            if (1<<v)&j!=0:
                vertexs.add(v) 
        return vertexs

    for j in range(1<<n): #状态
        for i in range(1,n):    # 城市
            vertexes=find_vertex(j)
            if i not in vertexes:
                for k in vertexes:
                    d[i][j] = min(d[i][j],d[k][j-(1<<k)]+c[k][i])

    # 计算最后每个城市回到0的距离
    res = 0xffffffff
    for k in range(1,n):
        res = min(res,d[k][(1<<n)-1-(1<<k)]+c[k][0])
    return res
if __name__ == "__main__":
    # c=[[0,3,1,5,8],[3,0,6,7,9],[1,6,0,4,2],[5,7,4,0,3],[8,9,2,3,0]]
    c = [[0, 3, 6,7],[5, 0,2,3],[6,4,0,2],[3,7,5,0]]
    print(TSP(c))


