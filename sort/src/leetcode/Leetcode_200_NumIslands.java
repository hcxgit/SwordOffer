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

        //1、终止条件： 结果在外层add
        //2、剪枝：越界 || 水
        if(!inArea(grid,row,line) || grid[row][line] != '1'){
            return;
        }

        // 3、选择
        // 1)、遍历过改为2
        grid[row][line] = '2';
        //2)、递归
        dfs(grid,row-1,line);
        dfs(grid,row+1,line);
        dfs(grid,row,line-1);
        dfs(grid,row,line+1);
        // 3、这里不用回溯
    }

    //是否在网格中
    public boolean inArea(char[][] grid,int row,int line) {
        return 0 <= row && row < grid.length && 0 <= line && line < grid[0].length;
    }
}
