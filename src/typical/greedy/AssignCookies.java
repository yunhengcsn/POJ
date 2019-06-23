package typical.greedy;

import java.util.Arrays;

/**
 * Description: 455. Assign Cookies
 *
 * @author csn
 */
public class AssignCookies {

    /**
     * Description: Just assign the cookies starting from the child with less greediness to maximize the number of happy children .
     * @param g
     * @param s
     * @return int
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        for(int j = 0;i < g.length && j < s.length; j++) {
            if(g[i] <= s[j]) i++;
        }
        return i;
    }
}
