package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author 三笠阿克曼
 * @date 2023/8/16
 * @description Leetcode 994: 腐烂的橘子
 *    1、bfs + 队列
 *      - 遍历，统计【新鲜橘子1的数量】，同时将【腐烂橘子2放入队列】
 *      - 当 num_1 >0 && !queue.isEmpty()：(好橘子坏橘子都有，开始扩散)
 *          - 每次取出【当前层数量】的【烂橘子】，【扩散】（上下左右）
 *              - 扩散时，如果是【好橘子1，则感染，并入队，num_1 --】
 *          - 【扩散次数+1】
 *      - 最后，如果1还有，则返回-1，否则返回扩散次数
 */
public class Leetcode_994_orangesRotting {
    int num_1 = 0;
    public int orangesRotting(int[][] grid) {

        Deque<int[]> queue = new LinkedList<>();

        // 统计1的数量，把2放入队列
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == 1){
                    num_1 ++;
                }else if(grid[i][j] == 2){
                    queue.add(new int[]{i,j});
                }
            }
        }

        int res = 0;
        // 有好橘子，且有坏橘子，开始感染
        while(num_1 >0 && !queue.isEmpty()){
            int num = queue.size();
            res ++;
            for(int i=0;i<num;i++){
                int[] curr_2 = queue.poll();
                int row = curr_2[0];
                int line = curr_2[1];
                // 扩散
                expend(grid,row+1,line,queue);
                expend(grid,row-1,line,queue);
                expend(grid,row,line+1,queue);
                expend(grid,row,line-1,queue);
            }
        }

        // 最后，如果1还有，则返回-1，否则返回扩散次数
        return num_1 !=0 ?-1:res;
    }

    // 如果是1，则感染，并加入queue，1的数量--
    public void expend(int[][] grid, int row, int line, Deque<int[]> queue){
        if(row <0 || row >= grid.length || line <0 || line >= grid[0].length){
            return;
        }

        if(grid[row][line] == 1){
            grid[row][line] = 2;
            queue.offer(new int[]{row,line});
            num_1 --;
        }
    }
}
