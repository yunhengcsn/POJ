package offer;

import java.util.ArrayList;

/**
 * Description:从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 *
 * @author csn
 */
public class PrintFromTopToBottom {
    class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public ArrayList<Integer> printFromTopToBottom(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        //用queue或list都可
        //Queue<TreeNode> q = new LinkedList<>();
        ArrayList<TreeNode> q = new ArrayList<>();
        q.add(root);
        while(!q.isEmpty()) {
            TreeNode curr = q.remove(0);
            ans.add(curr.val);
            if(curr.left != null) q.add(curr.left);
            if(curr.right != null) q.add(curr.right);
        }
        return ans;
    }
}
