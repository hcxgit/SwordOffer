package leetcode;

/**
 * @author 三笠阿克曼
 * @date 2022/3/8
 * Leetcode 39 ：组合总数
 */
public class Leetcode_79_exist {

    public boolean exist(char[][] board, String word) {

        // 上下左右 (-1,0),(1,0),(0,-1),(0,1)
        Integer[][] rowLine = new Integer[][]{{-1,0},{1,0},{0,-1},{0,1}};
        boolean[][] used = new boolean[board.length][board[0].length];

        //起始值
        for(int i=0; i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(dfs(i,j,0,used,board,word,rowLine)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(int row, int line,int index, boolean[][] used, char[][] board, String word, Integer[][] rowLine){
        //2、剪枝：越界 || 字母已经用过|| 字母不对
        if(row < 0 || row >= board.length || line <0 || line >= board[0].length || used[row][line] || board[row][line] != word.charAt(index)){
            return false;
        }
        //1、终止条件: 字母都找完 && 最后一个一样
        if(index == word.length()-1 && board[row][line] == word.charAt(index)){
            return true;
        }

        //3、选择：上下左右
        for(Integer[] rl:rowLine){
            int nextRow = row + rl[0];
            int nextLine = line + rl[1];
            //1)、处理当前节点
            used[row][line] = true;
            //2)、递归
            boolean res = dfs(nextRow,nextLine,index+1,used,board,word,rowLine);
            if(res){
                return true;
            }else{
                //3)、回溯
                used[row][line] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Leetcode_79_exist case1 = new Leetcode_79_exist();
        char[][] board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "SEE";
        System.out.println(case1.exist(board,word));
    }
}
