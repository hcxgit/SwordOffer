package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/8/14
 * @description Leetcode 547: 省的数量
 *
 * 1、dfs
 * 2、并查集
 */
public class Leetcode_547_findCircleNum {
    /**
     1、dfs
     */
    public int findCircleNum_1(int[][] isConnected) {
        /**
         1、dfs(沉没陆地法)
         -对每个起点，res ++, 然后dfs遍历，【遍历到的边删除】（包括节点自身的相连）.
         */
        int n = isConnected.length;
        int res = 0;
        for(int i=0;i<n;i++){
            if(isConnected[i][i] == 1){
                res ++;

                // 与i 直接/间接相连的都删除
                dfs(isConnected,i,n);
            }
        }
        return res;
    }

    // 与 i 直接或者间接相连的都删除
    public void dfs(int[][] isConnected, int i,int n){
        isConnected[i][i] = 0;
        for(int j=0;j<n;j++){
            if(isConnected[i][j] == 1){
                // 删除i-j
                isConnected[i][j] = 0;
                isConnected[j][i] = 0;
                // 删除与j相连的边
                dfs(isConnected,j,n);
            }
        }
    }

    /**
     2、并查集
     */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] parent = new int[n];

        // add
        for(int i=1;i<n;i++){
            parent[i] = i;
        }

        // union : 遍历一半就行
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(isConnected[i][j] == 1){
                    union(parent,i,j);
                }
            }
        }

        // 祖先节点的数量就是结果
        int res = 0;
        for(int i=0;i<n;i++){
            if(parent[i] == i){
                res++;
            }
        }

        return res;
    }

    public int find(int[] parent,int x){
        int root = x;
        while(parent[root] != root){
            root = parent[root];
        }

        // 路径压缩
        if(root != x){
            parent[x] = root;
        }
        return root;
    }

    public void union(int[] parent,int x,int y){
        int rootX = find(parent,x);
        int rootY = find(parent,y);

        if(rootX != rootY){
            parent[rootY] = rootX;
        }
    }
}
