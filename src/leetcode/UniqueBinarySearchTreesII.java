package leetcode;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTreesII {
/**
 * Description:Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 * Example:
 * Input: 3
 * Output: * [[1,null,3,2],[3,2,null,1],[3,1,null,null,2],[2,1,3],[1,null,2,null,3]]
 * Explanation:
 * The above output corresponds to the 5 unique BST's shown below:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * */
    private static List<TreeNode> generateTrees(int n) {
        ArrayList<ArrayList<ArrayList<TreeNode>>> dp = new ArrayList<>();
        //初始化dp0
        ArrayList<ArrayList<TreeNode>> tmpp = new ArrayList<>();
        ArrayList<TreeNode> tmplistt = new ArrayList<>();
        tmpp.add(tmplistt);
        dp.add(tmpp);
        //初始化dp1
        dp.add(new ArrayList<>());
        for(int i=1;i<=n;i++){
            TreeNode tmp = new TreeNode(i);
            ArrayList<TreeNode> tmplist = new ArrayList<>();
            tmplist.add(tmp);
            dp.get(1).add(tmplist);
        }
        //初始化dp的2~n
        for(int i=2;i<n+1;i++){
            ArrayList<ArrayList<TreeNode>> tmp = new ArrayList<>();
            for(int j=0;j<=n-i;j++) tmp.add(new ArrayList<>());
            dp.add(tmp);
        }
        //更新dp
        for(int k=2;k<=n;k++){
            helper(dp,k,n);//使用k个节点可以生成的BST
        }
        return dp.get(n).get(0);
    }
    private static void helper( ArrayList<ArrayList<ArrayList<TreeNode>>> dp, int k,int total){
        for(int i = 1;i <= total-k+1;i++){//k个数中最小为i  eg：12（i=1） 23（i=2）
            for(int j = i;j < i+k;j++){//根节点的值
                ArrayList<TreeNode> tmp=new ArrayList<>();
                tmp.add(null);
                ArrayList<TreeNode> left = (j-i)==0?tmp:dp.get(j-i).get(i-1);//j左边有j-i个节点，其中最小为i
                ArrayList<ArrayList<TreeNode>> tmp1=dp.get(i+k-j-1);//j右边有i+k-j-1个节点，其中最小为j+1
                ArrayList<TreeNode> right=(i+k-j-1)==0?tmp:tmp1.get(j);

                for(TreeNode l:left){
                    for(TreeNode r:right){
                        TreeNode root=new TreeNode(j);
                        root.left=l;
                        root.right=r;
                        dp.get(k).get(i-1).add(root);
                    }
                }
            }
        }
    }

}

class TreeNode {
     private int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}
