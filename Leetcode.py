import collections

class solution:

    # -----------------Leetcode--------------
    # ------N皇后--------

    def solveQueens(self, n):   # dfs解法
        def DFS(queens, xy_dif, xy_sum):   # xy_dif: 撇这条线，xy_sum: 捺这条线
            p = len(queens)  # 每行一个皇后，所以个数可以表示行号
            if p == n:
                result.append(queens)
                return None
            for q in range(n):  # 列号
                if q not in queens and p-q not in xy_dif and p+q not in xy_sum:
                    DFS(queens+[q], xy_dif+[p-q], xy_sum+[p+q])
        result = []
        DFS([], [], [])
        return [['.'*i + 'Q' + '.'*(n-i-1) for i in sol] for sol in result]

    def solveQueens_2(self, n):  # 位运算
        def DFS(row, cols, pie, na):
            """
            :type row: 当前放置皇后的行号
            :type cols: 列被占据的情况 [1 = 被占据，0 = 未被占据]
            :type na: 主对角线对应的列占据情况 [1 = 被占据，0 = 未被占据]
            :type pie: 次对角线对应的列占据情况 [1 = 被占据，0 = 未被占据]
            :rtype: 所有可行解的个数
            """
            if row == n:
                self.count += 1
                return
            # 得到当前所有空位
            # 1、 ((1 << n) - 1): 棋盘所有列都可放置 eg:n=4, 1111
            # 2、 cols | pie | na 合并 eg: 10 | 1 | 100| -> 111 表示被占据
            # ~:取反 因为 1中 1111表示空位，2中 111表示被占据也就是非空位，所以要取反，1表示空位
            free_columns = (~(cols | pie | na)) & ((1 << n) - 1)

            while free_columns:
                curr_column = free_columns & -free_columns  # 取到最低位1，也就是最低位放棋子
                # 更新占据情况， pie对应的列左移，na对应的列右移
                DFS(row + 1, cols | curr_column, (pie | curr_column) << 1, (na | curr_column) >> 1)
                free_columns = free_columns & (free_columns - 1)  # 去掉最低位的1，
        if n < 1: return []
        self.count = 0
        DFS(0, 0, 0, 0)
        return self.count

    # ------二维网格中的单词搜索问题（字典树解决）---------------
    END_OF_WORD = '#'
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    def findWords(self,board,words):
        if not board or not board[0] or not words:
            return []

        self.result = set()

        root = collections.defaultdict()
        for word in words:
            node = root
            for char in word:
                node = node.setdefault(char, collections.defaultdict())
            node[END_OF_WORD] = END_OF_WORD
        self.m, self.n = len(board), len(board[0])

        for i in range(self.m):
            for j in range(self.n):
                if board[i][j] in root:
                    self._dfs(board, i, j, '',root)
    def _dfs(self,board,i, j, cur_word, cur_dict):
        cur_word += board[i][j]
        cur_dict = cur_dict[board[i][j]]

        if END_OF_WORD in cur_dict:
            self.result.add(cur_word)

        tmp, board[i][j] = board[i][j], '@'  # '@'来标记是否被访问过
        for k in range(4):
            x, y = i+dx[k], j+dy[k]
            if 0 <= x < self.m and 0 <= y < self.n and board[x][y] != '@' and board[x][y] in cur_dict:
                self._dfs(board, x, y, cur_word, cur_dict)
        board[i][j] = tmp

    # ----191、计算数字二进制中的1的个数--------
    def count_one(self, x):
        num = 0
        while x != 0:
            if x & 1 == 1:   # 相当于 if x%2 == 1
                num += 1
            x = x >> 1
        return num
    def count_one_2(self,x):
        num = 0
        while x != 0:
            x = x & (x-1)  # 将最低位的1清0
            num += 1
        return num

    # --200、计算岛屿的数量: 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格-----------
    def numIslands(self, grid):
        """
        :type grid: List[List[str]]
        :rtype: int
        """
        # ----BFS-----
        if not grid or not grid[0]:
            return 0
        dir = [(-1, 0), (1, 0), (0, -1), (0, 1)]  # 上下左右四个方向
        res = 0

        m, n = len(grid), len(grid[0])
        q = []
        for i in range(m):
            for j in range(n):
                if grid[i][j] == '1':
                    res += 1
                    q.append((i, j))
                    grid[i][j] = '0'
                while q:
                    x_, y_ = q.pop(0)
                    for k in range(4):  # 看上下左右是否有陆地
                        x, y = x_ + dir[k][0], y_ + dir[k][1]
                        if 0 <= x < m and 0 <= y < n and grid[x][y] == '1':  # 有陆地就沉了，防止重复计算
                            q.append((x, y))
                            grid[x][y] = '0'
        return res
    # ----DFS-----
    def numIslands_DFS(self,grid):
        def DFS(x, y):
            if 0 <= x < m and 0 <= y < n and grid[x][y] == '1': # 有陆地就沉了，防止重复计算
                grid[x][y] = '0'
                for x_y in dir:
                    DFS(x+x_y[0], y+x_y[1])

        if not grid or not grid[0]:
            return 0
        dir = [(-1, 0), (1, 0), (0, -1), (0, 1)]
        res = 0

        for i in range(len(grid)):
            for j in range(len(grid[0])):
                if grid[i][j] == '1':
                    res += 1
                    DFS(i, j)
        return res

    # ----146、LRU缓存机制--------
    # 1、有序字典

    # def __init__(self, capacity):  # 缓存容量
    #     import collections
    #     self.cache = collections.OrderedDict()
    #     self.remain = capacity

    def get(self, key):
        if key not in self.cache:
            return -1
        v = self.cache.pop(key)
        self.cache[key] = v  # 将key更新为最新的
        return v

    def put(self, key, value):
        if key in self.cache:
            self.cache.pop(key)
        else:
            if self.remain > 0:
                self.remain -= 1
            else:  # 缓存满了
                self.cache.popitem(last=False)
        self.cache[key] = value
    # 2、双向链表+hashMap
    """  
    class ListNode():
        def __init__(self, key=None, value=None):
            self.key = key
            self.value = value
            self.pre = None
            self.next = None
    
    def __init__(self, capacity):
        self.remain = capacity
        self.head = ListNode()  # 新建两个节点head和tail
        self.tail = ListNode()
        self.head.next = self.tail  # 初始化链表为 head <-> tail
        self.tail.pre = self.head
        self.hashmap = {}
    """
    def get(self, key):
        if key in self.hashmap:
            self.move_node_to_tail(self.hashmap[key]) # 如果已经在链表中了久把它移到末尾（变成最新访问的）
            return self.hashmap[key].value
        else:
            return -1
    def put(self, key, value):
        if key in self.hashmap:
            self.hashmap[key].value = value
            self.move_node_to_tail(self.hashmap[key])
        else:
            if self.remain > 0:
                self.remain -= 1
            else:
                self.hashmap.pop(self.head.next.key)  # 去掉最久没有被访问过的节点，即头节点之后的节点
                self.head.next = self.head.next.next
                self.head.next.pre = self.head
            new = ListNode(key, value)
            self.hashmap[key] = new
            new.next = self.tail
            new.pre = self.tail.pre
            new.pre.next = new
            self.tail.pre = new
    def move_node_to_tail(self, node):  # 将节点移到尾节点之前
        node.pre.next = node.next
        node.next.pre = node.pre
        node.next = self.tail
        self.tail.pre.next = node
        node.pre = self.tail.pre
        self.tail.pre = node

    # -------数组中只出现一次的两个数字---------
    # 返回[a,b] 其中ab是出现一次的两个数字
    def FindNumsAppearOnce(self, array):
        # write code here
        if len(array) < 2:
            return []
        res = self.FindNum(array)  # 最终两数异或结果
        res = res & (-res)  # 取最后一位1，
        res1, res2 = 0, 0
        for i in array:
            if (i & res) == 0:  # 那一位为0 与res1异或
                res1 ^= i
            else:
                res2 ^= i
        return [res1, res2]
    def FindNum(self, array):
        res = 0
        for i in array:
            res ^= i
        return res

# -*- coding:utf-8 -*-
# 1、环形链表

class Solution:
    class NodeList:
        def __init__(self, val):
            self.val = val
            self.next = None
    def LastRemaining_Solution(self, n, m):
        # write code here
        if n < 1 or m < 1:
            return False
        head = self.NodeList(0)
        curr = head
        for i in range(1,n):
            newNode = self.NodeList(i)
            curr.next = newNode
            curr = newNode
        curr.next = head
        for j in range(n-1):
            for i in range(m-2):
                head = head.next
            head.next = head.next.next
            head = head.next
        return head.val

# -------980、不同的路径---------
# 1、动态规划

    def uniquePathsIII(self, grid):
        self.grid = grid
        self.row = len(grid)
        self.line = len(grid[0])
        target = 0   # 要走的路  eg: 1100010

        for i in range(self.row):
            for j in range(self.line):
                if self.grid[i][j] == 0:
                    target |= self.code(i,j)
                elif self.grid[i][j] == 1:  # 入口
                    self.start_r, self.start_l = i,j
                elif self.grid[i][j] == 2:  # 出口
                    target |= self.code(i, j)
                    self.end_r, self.end_l = i, j

        return self.dp(self.start_r, self.start_l, target)


    def dp(self, r, l, togo):
        if r == self.end_r and l == self.end_l:
            return +(togo==0)

        res = 0
        for i,j in [(r+1,l),(r-1,l),(r,l-1),(r,l+1)]:
            if self.rightNeighbors(i,j) and togo & self.code(i,j):  # 位置合法并且没有走过
                res += self.dp(i,j,togo^self.code(i,j))  # 用异或将该合法位置归0
        return res

    def code(self,r,l):
        """
        将（r,l）映射到一维：  eg: 1100110  1表示可以走 
        :param r: 
        :param l: 
        :return: 
        """
        return 1 << (r * self.line + l)

    def rightNeighbors(self,r,l):
        """
        返回合法的坐标，防止出界
        :param r: 
        :param l: 
        :return: 
        """
        if 0 <= r < self.row and 0 <= l < self.line and self.grid[r][l]%2 == 0:
            return True
        else:
            return False
# 2、dfs回溯法

    def uniquePathsIII(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: int
        """
        start, end, p = None, None, 1
        row, col = len(grid), len(grid[0])
        for i in range(row):
            for j in range(col):
                if grid[i][j] == 1:
                    start = i, j
                elif grid[i][j] == 2:
                    end = i, j
                elif grid[i][j] == 0:
                    p += 1

        def dfs(x, y, p):
            if not (0 <= x < row and 0 <= y < col and grid[x][y] >= 0):
                return 0
            if end == (x, y) and p == 0:
                return 1
            grid[x][y] = -1
            res = dfs(x + 1, y, p - 1) + dfs(x, y + 1, p - 1) + dfs(x - 1, y, p - 1) + dfs(x, y - 1, p - 1)
            grid[x][y] = 0
            return res

        return dfs(start[0], start[1], p)


if __name__ == "__main__":
    # s = Solution()

    import sys

    line = list(map(int, sys.stdin.readline().strip().split()))
    # return (p*f+d)//(p+x)
    print((line[3] * line[1] + line[2])//(line[3]+line[0]))

