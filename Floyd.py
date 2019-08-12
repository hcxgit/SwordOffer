    
# Floyd 求任意两点间的最短距离、路径
class Floyd:
    def __init__(self,N,alist):  
        """
        N:节点个数
        alist: 相连的节点以及之间的权重  [[node1,node2,value]]
        """
        MAX = 0xffffffff
        self.N = N
        self.dp = [[MAX if i != j else 0 for i in range(N)]for j in range(N)]  # dp[i][j]存储i到j的最短距离
        self.path = [[-1 for i in range(N)]for j in range(N)]  # path[i][j]=k 表示i到j的最短路径是经过顶点k

        for i in alist:
            self.dp[i[0]-1][i[1]-1] = i[2]
            self.path[i[0]-1][i[1]-1] = i[1]-1
    def _init_Floyd(self):
        for i in range(self.N):
            for j in range(self.N):
                for k in range(self.N):
                    temp = self.dp[i][k]+self.dp[k][j]
                    if self.dp[i][j] > temp:
                        self.dp[i][j] = temp
                        self.path[i][j] = self.path[i][k]
        return self.dp, self.path

    def return_path(self,start,end):
        """
        返回最短路径
        """
        res = [start]
        start,end = start-1,end-1  # 从0开始
        while True:
            mid = self.path[start][end]
            if mid == end:
                res.append(end+1)
                break
            else:
                res.append(mid+1)
                start = mid
        return res
if __name__ == "__main__":
    alist = [
        [1,2,1],
        [2,3,1]
    ]
    floyd = Floyd(3,alist)
    dp, path = floyd._init_Floyd()
    print(dp)
    print(path)
    route = floyd.return_path(1,3)
    print(route)
