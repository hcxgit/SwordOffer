package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2023/6/26
 * @description Leetcode 695: 岛屿的最大面积
 * 1、回溯
 */
public class Leetcode_695_maxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        /**
         1、回溯(dfs)
         */

        int res = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == 1){
                    res = Math.max(res,dfs(grid,i,j));
                }
            }
        }

        return res;
    }

    /**
     1、回溯(dfs):
     - 沉没岛屿法：遍历过的改为2
     */
    public int dfs(int[][] grid, int row,int line){
        // 1、结束条件: 外面计算max
        // 2、剪枝:越界|| 不为1
        if(row < 0 || row >= grid.length || line < 0 || line >= grid[0].length || grid[row][line] != 1){
            return 0;
        }

        int currSum = 1;
        grid[row][line] = 2;

        // 3、选择: 四个方向的和
        currSum += dfs(grid,row - 1,line);
        currSum += dfs(grid,row + 1,line);
        currSum += dfs(grid,row,line - 1);
        currSum += dfs(grid,row,line + 1);
        return currSum;
    }
}
