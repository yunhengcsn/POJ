package leetcode;

import java.util.ArrayList;

public class SumRoottoLeafNumbers {
    //Definition for a binary tree node.
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    /*129. Sum Root to Leaf Numbers
    * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
    * An example is the root-to-leaf path 1->2->3 which represents the number 123.
    * Find the total sum of all root-to-leaf numbers.
    * Note: A leaf is a node with no children.
    * Example :Input: [4,9,0,5,1]
    *     4
    *    / \
    *   9   0
    *  / \
    * 5   1
    * Output: 1026
    * Explanation:
    * The root-to-leaf path 4->9->5 represents the number 495.The root-to-leaf path 4->9->1 represents the number 491.
    * The root-to-leaf path 4->0 represents the number 40.
    * Therefore, sum = 495 + 491 + 40 = 1026.
    * */
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;//root为空则直接返回
        return dfs(root,0);
    }
    private int dfs(TreeNode root,int x) {//x表示前几位数字表示的数
        if(root.left == null && root.right == null) {
            return x * 10 + root.val ;//叶子节点
        }
        int v = 0;
        if(root.left != null) {
            v += dfs(root.left,x * 10 + root.val);//v加上左子树的和
        }
        if(root.right != null) {
            v += dfs(root.right,x * 10 + root.val);//v加上右子树的和
        }
        return v;
    }

    //so elegant!!!
    public int sumNumbersII(TreeNode root) {
        return sum(root, 0);
    }
    private int sum(TreeNode n, int s){
        if (n == null) return 0;
        if (n.right == null && n.left == null) return s*10 + n.val;
        return sum(n.left, s*10 + n.val) + sum(n.right, s*10 + n.val);
    }

    // My Solution
    private int sum = 0;//和
    private int n = 0;//要被求和的数字
    public int sumNumbersIII(TreeNode root) {
        ArrayList<Integer> numlist = new ArrayList<>(); //数字列表 1->2->3 123
        if(root == null) return 0;//root为空则直接返回
        dfs(root,numlist);
        return sum;
    }
    private void dfs(TreeNode root, ArrayList<Integer> numlist) {
        numlist.add(root.val);
        n = n * 10 + root.val;//更新n
        //当前节点为叶子节点时更新sum
        if(root.left == null && root.right == null) {
            sum = sum + n;
            return;
        }
        if(root.left != null) {
            dfs(root.left,numlist);
            int last = numlist.remove(numlist.size()-1);
            n = (n - last) / 10;
        }
        if(root.right != null) {
            dfs(root.right,numlist);
            int last = numlist.remove(numlist.size()-1);
            n = (n - last) / 10;
        }

    }
}
