package offer;

/**
 * @BelongsProject: POJ
 * @BelongsPackage: offer
 * @Author: csn
 * @Description: 操作给定的二叉树，将其变换为源二叉树的镜像。
 */
public class Mirror {

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    //递归
    private void mirror(TreeNode root) {
        if(root == null) return;
        if(root.left != null || root.right != null) {
            //交换
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;

            mirror(root.left);
            mirror(root.right);
        }
    }
}
