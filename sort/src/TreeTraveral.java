import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author 三笠阿克曼
 * @date 2021/10/22
 */
public class TreeTraveral {

    //先序遍历：递归
    public static void preOrder(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }

        list.add(root.getdata());
        if (root.getLeft() != null) {
            preOrder(root.getLeft(), list);
        }
        if (root.getRight() != null) {
            preOrder(root.getRight(), list);
        }
    }

    //先序遍历：非递归   1、访问根节点 2、右子树压栈，左子树压栈 3、出栈
    public static void preOrder2(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        TreeNode temp = null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            temp = stack.pop();
            list.add(temp.getdata());
            if (temp.getRight() != null) {
                stack.push(temp.getRight());
            }
            if (temp.getLeft() != null) {
                stack.push(temp.getLeft());
            }
        }
    }

    //中序遍历：递归
    public static void midOrder(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }

        if (root.getLeft() != null) {
            midOrder(root.getLeft(), list);
        }
        list.add(root.getdata());
        if (root.getRight() != null) {
            midOrder(root.getRight(), list);
        }
    }

    //中序遍历：非递归   1、一直找左子树压栈，找不到后出栈，访问值，右子树压栈，继续循环
    public static void midOrder2(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        TreeNode temp = null;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {

            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }

            if (!stack.isEmpty()) {
                temp = stack.pop();
                list.add(temp.getdata());
                root = temp.getRight();
            }
        }
    }


    //后序遍历：递归
    public static void postOrder(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }

        if (root.getLeft() != null) {
            postOrder(root.getLeft(), list);
        }
        if (root.getRight() != null) {
            postOrder(root.getRight(), list);
        }
        list.add(root.getdata());
    }

    public static void levelOrder(TreeNode root, ArrayList<Integer> res) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res.add(node.getdata());
            if (node.getLeft() != null) {
                queue.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.offer(node.getRight());
            }
        }
    }

    public static void main(String[] args) {
        TreeNode node5 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(node4, node5, 2);
        TreeNode node1 = new TreeNode(node2, node3, 1);

        ArrayList list = new ArrayList<Integer>(5);
//        preOrder(node1, list);
//        preOrder2(node1, list);
//        midOrder(node1, list);
        midOrder2(node1, list);
//        postOrder(node1, list);
//        levelOrder(node1, list);
        System.out.println(list);
    }
}
