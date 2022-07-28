package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/7/28
 *
 * Leetcode 200: 岛屿数量
 *
 *  1、dfs/bfs
 *      对每个[1]的格子进行dfs，遍历过的置为【2】。
 *      注意：上下左右【边界】。
 */
public class Leetcode_200_NumIslands {
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int line = 0; line < grid[0].length; line++) {
                if(grid[row][line] == '1'){
                    res ++;
                    dfs(grid,row,line);
                }
            }
        }
        return res;
    }

    public void dfs(char[][] grid,int row,int line) {

        //越界
        if(!inArea(grid,row,line)){
            return;
        }
        //不是陆地
        if(grid[row][line] != '1'){
            return;
        }

        // 遍历过改为2
        grid[row][line] = '2';
        //遍历上下左右
        dfs(grid,row-1,line);
        dfs(grid,row+1,line);
        dfs(grid,row,line-1);
        dfs(grid,row,line+1);
    }

    //是否在网格中
    public boolean inArea(char[][] grid,int row,int line) {
        return 0 <= row && row < grid.length && 0 <= line && line < grid[0].length;
    }
}
