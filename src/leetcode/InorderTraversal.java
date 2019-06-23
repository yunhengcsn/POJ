package leetcode;
import java.util.ArrayList;
import java.util.List;

public class InorderTraversal {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
  /**
   * Description:Given a binary tree, return the inorder traversal of its nodes' values.
   * 树的中序遍历
   * */
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list=new ArrayList<>();
        if(root==null) return list;
        helper(root,list);
        return list;
    }
    private void helper(TreeNode root,ArrayList<Integer> list){
        if(root.left!=null) helper(root.left,list);
        list.add(root.val);
        if(root.right!=null) helper(root.right,list);
    }
}
