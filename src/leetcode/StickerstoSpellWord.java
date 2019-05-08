package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class StickerstoSpellWord {
    /*691. Stickers to Spell Word
    * We are given N different types of stickers. Each sticker has a lowercase English word on it.
    * You would like to spell out the given target string by cutting individual letters from your collection of stickers and rearranging them.
    * You can use each sticker more than once if you want, and you have infinite quantities of each sticker.
    * What is the minimum number of stickers that you need to spell out the target? If the task is impossible, return -1.
    * Example 1:     Input:   ["with", "example", "science"], "thehat"
    * Output: 3
    * Explanation:
    * We can use 2 "with" stickers, and 1 "example" sticker.
    * After cutting and rearrange the letters of those stickers, we can form the target "thehat".
    * Also, this is the minimum number of stickers necessary to form the target string.
    * Example 2:    Input:   ["notice", "possible"], "basicbasic"
    * Output: -1
    * Explanation:
    * We can't form the target "basicbasic" from cutting letters from the given stickers.
    *
    * Solution: bfs
    * */


    // use bfs, put every left string in queue using each sticker
    // when every character number is 0, means we spell the word
    public int minStickers(String[] stickers, String target) {
        int min = 0;
        if (target == null || target.length() == 0) {
            return min;
        }
        Queue<int[]> queue = new LinkedList<>();
        int[] tMap = new int[26];
        for (int i = 0; i < target.length(); i++) { // construct map for target
            tMap[target.charAt(i) - 'a']++;
        }
        int sLen = stickers.length;
        int[][] sMap = new int[sLen][26];
        for (int i = 0; i < sLen; i++) { // construct map for stickers
            for (int j = 0; j < stickers[i].length(); j++) {
                sMap[i][stickers[i].charAt(j) - 'a']++;
            }
        }
        queue.offer(tMap);
        Set<String> set = new HashSet<>(); // record left over substring we've already spelled
        while (!queue.isEmpty()) {
            min++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                String curString = toSortedString(cur); // we sort string to be put in the set
                if (set.add(curString)) { // check if we already spell current string
                    for (int j = 0; j < sLen; j++) {
                        // if current sticker does not have the first char in current string, it is impossible for that sticker to have following char, so continue
                        if (sMap[j][curString.charAt(0) - 'a'] == 0) {
                            continue;
                        }
                        int[] temp = cur.clone();
                        for (int k = 0; k < 26; k++) {
                            // only update temp when the current sticker has the char
                            if (temp[k] > 0) {
                                int left = temp[k] - sMap[j][k];//target用当前sticker表示后剩下的字符数
                                temp[k] = left > 0 ? left : 0;
                            }
                        }
                        // if temp doesn't have any char left, it means we spell the word, and the number of stickers we used must be the minimum
                        if (wordIsSpelled(temp)) {
                            return min;
                        }
                        queue.offer(temp);
                    }
                }
            }
        }
        return -1;
    }
    //将数组转为String
    private String toSortedString(int[] word) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < word[i]; j++) {
                sb.append((char) (i + 'a'));
            }
        }
        return sb.toString();
    }
    //判断word是否spelled 所有字母次数为0则为true
    private boolean wordIsSpelled(int[] word) {
        for (int value : word) {
            if (value > 0) {
                return false;
            }
        }
        return true;
    }
}
