package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2021/12/27
 * Leetcode: 130 寻找被包围的区域
 *
 * 1、 dfs : 寻找【与边界'O'相连的】，【替换为'a'】，最后【遍历整体替换】
 */
public class Leetcode_130_solve {
    public void solve(char[][] board) {

        // 找和第一列或者最后一列相连的'O'
        for(int i=0;i<board.length;i++){
            dfs(i,0,board);
            dfs(i,board[0].length-1,board);
        }


        // 找和第一行或者最后一行相连的'O'
        for(int j=0;j<board[0].length;j++){
            dfs(0,j,board);
            dfs(board.length-1,j,board);
        }

        // 整体替换
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]=='a'){
                    board[i][j]='O';
                }else{
                    board[i][j]='X';
                }
            }
        }
    }

    /*
     ** 将与边界相连的'O'都替换为'a'
     */
    public void dfs(int row, int line,char[][]board){
        //剪枝: 越界 || 'X' || 'a'替换过了
        if(row < 0|| row >= board.length || line < 0 || line >= board[0].length || board[row][line] == 'X'|| board[row][line] == 'a'){
            return;
        }
        board[row][line]= 'a';
        // 上下左右递归
        dfs(row-1,line,board);
        dfs(row+1,line,board);
        dfs(row,line-1,board);
        dfs(row,line+1,board);
    }
}
