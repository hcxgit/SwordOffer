package datastruct;

/**
 * @author 三笠阿克曼
 * @date 2021/10/22
 */
public class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int data;

    TreeNode() {};

    public TreeNode(int data) {
        this.data = data;
    }

    public TreeNode(TreeNode left, TreeNode right, int data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }
}
