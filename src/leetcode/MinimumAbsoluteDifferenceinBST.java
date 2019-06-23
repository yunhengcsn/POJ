package leetcode;
/*

 *Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.
 *
 * Example:
 *
 * Input:
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * Output:
 * 1
 *
 * Explanation:
 * The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 */



import java.util.ArrayList;

public class MinimumAbsoluteDifferenceinBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int getMinimumDifference(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        inorder(root,list);
        int min = Integer.MAX_VALUE;
        for(int i = 1;i<list.size();i++) {
            int tmp = list.get(i)-list.get(i-1);
            min = Math.min(min,tmp);
        }
        return min;
    }
    private void inorder(TreeNode root,ArrayList<Integer> list) {
        if(root == null) return;
        inorder(root.left,list);
        list.add(root.val);
        inorder(root.right,list);
    }
}
