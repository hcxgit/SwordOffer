package datastruct;

import java.util.*;

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

        list.add(root.data);
        if (root.left != null) {
            preOrder(root.left, list);
        }
        if (root.right != null) {
            preOrder(root.right, list);
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
            list.add(temp.data);
            if (temp.right != null) {
                stack.push(temp.right);
            }
            if (temp.left != null) {
                stack.push(temp.left);
            }
        }
    }

    //中序遍历：递归
    public static void midOrder(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            midOrder(root.left, list);
        }
        list.add(root.data);
        if (root.right != null) {
            midOrder(root.right, list);
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
                root = root.left;
            }

            if (!stack.isEmpty()) {
                temp = stack.pop();
                list.add(temp.data);
                root = temp.right;
            }
        }
    }

    //Morris 中序遍历：将二叉树退化成链表，全连接在右子树
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode pre = null;
        while(root!=null) {
            //如果左节点不为空，就将当前节点连带右子树全部挂到
            //左节点的最右子树下面
            if(root.left!=null) {
                pre = root.left;
                //找到左节点的最右子树
                while(pre.right!=null) {
                    pre = pre.right;
                }
                //root挂到最右子树下面
                pre.right = root;
                //root的left置为新的root
                TreeNode preRoot = root;
                root = root.left;
                //原root左子树置为null
                preRoot.left = null;
                //左子树为空，则打印这个节点，并向右边遍历
            } else {
                res.add(root.data);
                root = root.right;
            }
        }
        return res;
    }

    //层序遍历
    public static void levelOrder(TreeNode root, ArrayList<Integer> res) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res.add(node.data);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
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
