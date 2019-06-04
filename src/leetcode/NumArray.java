package leetcode;

/**
 * Description: 307. Range Sum Query - Mutable【segment tree/树状数组】
 *              Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *               The update(i, val) function modifies nums by updating the element at index i to val.
 *
 * @author csn
 */
public class NumArray {

    //1.naive
    class  NumArray1 {
        private int[] nums;

        public NumArray1(int[] nums) {
            this.nums = nums;
        }

        public void update(int i, int val) {
            nums[i] = val;
        }

        public int sumRange(int i, int j) {
            int sum = 0;
            for(int k = i; k <= j; k++) {
                sum += nums[k];
            }
            return sum;
        }
    }

    //2.Sqrt Decomposition, 计算分块和
    class  NumArray2 {
        private int[] b;
        private int len;
        private int[] nums;

        public NumArray2(int[] nums) {
            this.nums = nums;
            double l = Math.sqrt(nums.length);
            len = (int) Math.ceil(nums.length/l);
            b = new int [len];
            for (int i = 0; i < nums.length; i++)
                b[i / len] += nums[i];
        }

        public int sumRange(int i, int j) {
            int sum = 0;
            int startBlock = i / len;
            int endBlock = j / len;
            if (startBlock == endBlock) {
                for (int k = i; k <= j; k++)
                    sum += nums[k];
            } else {
                for (int k = i; k <= (startBlock + 1) * len - 1; k++)
                    sum += nums[k];
                for (int k = startBlock + 1; k <= endBlock - 1; k++)
                    sum += b[k];
                for (int k = endBlock * len; k <= j; k++)
                    sum += nums[k];
            }
            return sum;
        }

        public void update(int i, int val) {
            int b_l = i / len;
            b[b_l] = b[b_l] - nums[i] + val;
            nums[i] = val;
        }
    }


    //3.segment tree  is used to solve numerous range query problems like
    //   finding minimum, maximum, sum, greatest common divisor, least common denominator in array in logarithmic time.
    class NumArray3 {
        int[] tree;
        int n;
        public NumArray3(int[] nums) {
            if (nums.length > 0) {
                n = nums.length;
                tree = new int[n * 2];
                buildTree(nums);
            }
        }
        private void buildTree(int[] nums) {
            for (int i = n, j = 0;  i < 2 * n; i++,  j++)
                tree[i] = nums[j];
            for (int i = n - 1; i > 0; --i)
                tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }

        public void update(int pos, int val) {
            pos += n;
            tree[pos] = val;
            while (pos > 0) {
                int left = pos;
                int right = pos;
                if (pos % 2 == 0) {
                    right = pos + 1;
                } else {
                    left = pos - 1;
                }
                // parent is updated after child is updated
                tree[pos / 2] = tree[left] + tree[right];
                pos /= 2;
            }
        }

        public int sumRange(int l, int r) {
            // get leaf with value 'l'
            l += n;
            // get leaf with value 'r'
            r += n;
            int sum = 0;
            while (l <= r) {
                if ((l % 2) == 1) {
                    sum += tree[l];
                    l++;
                }
                if ((r % 2) == 0) {
                    sum += tree[r];
                    r--;
                }
                l /= 2;
                r /= 2;
            }
            return sum;
        }
    }


    //4.bit indexed tree 树状数组
    class NumArray4 {
        int[] nums;
        int[] BIT;
        int n;

        public NumArray4(int[] nums) {
            this.nums = nums;

            n = nums.length;
            BIT = new int[n + 1];
            for (int i = 0; i < n; i++)
                init(i, nums[i]);
        }

        private void init(int i, int val) {
            i++;
            while (i <= n) {
                BIT[i] += val;
                i += (i & -i);//加上i的二进制末尾0个数
            }
        }

        void update(int i, int val) {
            int diff = val - nums[i];
            nums[i] = val;
            init(i, diff);
        }

        private int getSum(int i) {
            int sum = 0;
            i++;
            while (i > 0) {
                sum += BIT[i];
                i -= (i & -i);
            }
            return sum;
        }

        public int sumRange(int i, int j) {
            return getSum(j) - getSum(i - 1);
        }
    }


}

/*
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */