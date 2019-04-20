package leetcode;

public class MedianofTwoSortedArrays {
    //O(m+n)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        int p1 = 0;
        int p2 = 0;
        int cnt = 0;
        int pre=0, after=0;
        while(cnt <= total/2) {
            int min;
            if(p1 >= nums1.length) min =nums2[p2++];
            else if(p2 >= nums2.length) min =nums1[p1++];
            else {
                if(nums1[p1] > nums2[p2]){
                    min = nums2[p2];
                    p2++;
                }else {
                    min = nums1[p1];
                    p1++;
                }
            }

            if(cnt == total/2-1) pre = min;
            if(cnt == total/2) after = min;
            cnt++;
        }
        double ans;
        if(total%2 == 0) ans = (pre + after)/2.0;
        else ans = after;
        return ans;
    }

    //O(log(m+n))
    //二分查找：Searching i in [0, m], to find an object `i` that:
    //    B[j-1] <= A[i] and A[i-1] <= B[j], ( where j = (m + n + 1)/2 - i )
    public double findMedianSortedArrays2(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

}
