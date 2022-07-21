package leetcode;

import datastruct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author 三笠阿克曼
 * @date 2022/5/8
 * LeetCode 101：树是否轴对称
 * 思路一：递归。
 *      树是否轴对称 ——> 左右子树镜像对称 ——> 两棵树镜像对称的条件：
 *          1、它们的两个根结点具有相同的值
 *          2、每个树的右子树都与另一个树的左子树镜像对称
 *
 * 思路二：迭代
 *      入队，每次出队比较两个相邻节点
 *      入队时顺序，root1.left、root2.right、root1.right、root2.left
 */
public class IsSymmetric {
    public static boolean isSymmetric(TreeNode root){
        return check(root.left,root.right);
    }

    //镜像对称，递归检查
    public static boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.data == q.data && check(p.left, q.right) && check(p.right, q.left);
    }

    //镜像对称，迭代检查
    public boolean check2(TreeNode u, TreeNode v) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(u);
        q.offer(v);
        while (!q.isEmpty()) {
            u = q.poll();
            v = q.poll();
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.data != v.data)) {
                return false;
            }

            q.offer(u.left);
            q.offer(v.right);

            q.offer(u.right);
            q.offer(v.left);
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s;
        while ((s=scanner.nextLine()) != null && !s.equals("")){
            System.out.println(s);
        }
    }
}
