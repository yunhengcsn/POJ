package leetcode;

import java.util.ArrayList;
/*236. Lowest Common Ancestor of a Binary Tree
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * According to the definition of LCA on Wikipedia:
 * &ldquo;The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants
 *  (where we allow a node to be a descendant of itself).&rdquo;
 * Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * */
public class LowestCommonAncestorofaBinaryTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    //1.low time complexity
    TreeNode ans;

    public LowestCommonAncestorofaBinaryTree(){
        ans=null;
    }
    private boolean  search(TreeNode node,TreeNode p,TreeNode q){
        if(node==null) return false;
        int left=search(node.left,p,q)?1:0;
        int right=search(node.right,p,q)?1:0;
        int mid=(node==p||node==q)?1:0;
        if(left+right+mid>=2) ans=node;
        return (mid+right+left)>0;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        search(root,p,q);
        return ans;
    }

    //2.recursive
    ArrayList<Integer> ppp = new ArrayList<>();
    ArrayList<Integer> qqq = new ArrayList<>();

    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null) return null;
        recur(root,p,q,new ArrayList<Integer>(),new ArrayList<Integer>());
        int ans = -1;
        for(int i = 0; i < ppp.size() && i < qqq.size();i++) {

            int p1 = ppp.get(i);
            int q1 = qqq.get(i);
            if(p1 != q1) break;
            ans = p1;
        }
        return new TreeNode(ans);
    }
    private void recur(TreeNode root,TreeNode p, TreeNode q,ArrayList<Integer> pPath,ArrayList<Integer> qPath) {
        if(root == null) return;
        if((p == null) &&(q == null)) return;
        TreeNode pp = p;
        TreeNode qq = q;
        if(p != null) pPath.add(root.val);
        if(q != null) qPath.add(root.val);

        if(p != null &&root.val == p.val) {
            pp = null;
            ppp.addAll(pPath);
        }
        if(q != null && root.val == q.val) {
            qq = null;
            qqq.addAll(qPath);
        }

        recur(root.left,pp,qq,pPath,qPath);
        recur(root.right,pp,qq,pPath,qPath);
        pPath.remove(new Integer(root.val));
        qPath.remove(new Integer(root.val));
    }
}

