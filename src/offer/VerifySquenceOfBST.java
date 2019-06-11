package offer;

/**
 * Description:输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 * 递归
 *
 * BST的后序序列的合法序列是，对于一个序列S，最后一个元素是x （也就是根），
 * 如果去掉最后一个元素的序列为T，那么T满足：T可以分成两段，前一段（左子树）小于x，后一段（右子树）大于x，且这两段（子树）都是合法的后序序列。
 * @author csn
 */
public class VerifySquenceOfBST {

    /**
     * 1.递归
     * @param sequence int[]
     * @return boolean
     */
    public boolean verifySquenceOfBST1(int [] sequence) {
        if(sequence.length == 0) return false;
        return recur(sequence,0,sequence.length-1);
    }

    private boolean recur(int[] sequence, int st, int ed) {
        if(st >= ed || st < 0 || ed >= sequence.length) return true;
        int root = sequence[ed];

        int mid = st;
        while(mid < sequence.length && sequence[mid] < root) mid++;

        while(mid < ed){
            if(sequence[mid++]< root)
                return false;
        }

        return recur(sequence,st,mid-1) & recur(sequence, mid,ed);
    }

    /**
     * 2.非递归
     * @param sequence int[]
     * @return boolean
     */
    public boolean verifySquenceOfBST2(int [] sequence) {
        int length = sequence.length;
        if(0 == length)return false;

        int i = 0;
        while(--length > 0)
        {
            while(i <= length && sequence[i] < sequence[length]) { i++; }
            while(i <= length && sequence[i] > sequence[length]) { i++; }

            if(i < length)return false;
            i=0;
        }
        return true;
    }
}
