def tsp(c):
    
    n=len(c)

    # 从0开始，只记录后面几个的状态，所以5个城市，状态最大1111
    d=[[0 for j in range(1<<(n-1))] for i in range(n)]
    for i in range(1,n): # 初始化，从0到i
        d[i][0]=c[i][0]

    def find_vertex(j):
        vertexs=[]#j包含哪几个顶点
        for v in range(n-1): # 不算第一个城市，所以n-1
            if (1<<v)&j!=0:
                vertexs.append(v+1) 
        return vertexs

    for j in range(1<<(n-1)):
        for i in range(1,n):
            temp=[]
            vertexes=find_vertex(j)
            if i not in vertexes:
                for k in vertexes:
                    temp.append(c[i][k]+d[k][j-(1<<(k-1))])
                if temp:
                    d[i][j]=min(temp)

    # 计算最后每个城市回到0的距离
    temp=0xffffffff
    j=2**(n-1)-1
    for k in range(1,n): 
        new_j=j-2**(k-1)
        temp = min(temp,c[0][k]+d[k][new_j])
    d[0][-1]=temp

    return d[0][-1]
if __name__ == "__main__":
    c=[[0,3,1,5,8],[3,0,6,7,9],[1,6,0,4,2],[5,7,4,0,3],[8,9,2,3,0]]
    print(tsp(c))