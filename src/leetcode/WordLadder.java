package leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordLadder {
    /*127. Word Ladder
    * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
    * Only one letter can be changed at a time.Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
    * Note:
    * Return 0 if there is no such transformation sequence.
    * All words have the same length.
    * All words contain only lowercase alphabetic characters.
    * You may assume no duplicates in the word list.
    * You may assume beginWord and endWord are non-empty and are not the same.
    * Example 1:
    * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
    * Output: 5
    * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
    * Example 2:
    * Input: beginWord = "hit" endWord = "cog" wordList = ["hot","dot","dog","lot","log"]
    * Output: 0
    * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
    *
    * Solution:双端bfs
    * */
    public int ladderLength(String b, String e, List<String> w) {
        Set<String> set = new HashSet<>(w), q1 = new HashSet<>(), q2 = new HashSet<>();
        if(!set.contains(e)) return 0;
        set.remove(e);

        q1.add(b);//beginWord集合
        q2.add(e);//endWord集合

        int dist = 1;
        while(q1.size() > 0 && q2.size() > 0) {//保证q1比q2元素少
            if(q1.size() > q2.size()) {
                Set<String> t = q1;
                q1 = q2;
                q2 = t;
            }
            Set<String> tmp = new HashSet<>();//新的beginWords
            for(String t : q1) {
                char[] a = t.toCharArray();
                for(int i = 0; i < a.length; i++) {
                    char orig = a[i];
                    //26个字母替换
                    for(int j = 0; j < 26; j++) {
                        a[i] = (char)(j+'a');
                        String nxt = new String(a);
                        if(q2.contains(nxt)) return dist + 1;//找到则return
                        if(!set.contains(nxt)) continue;//词典里没有的词跳过
                        set.remove(nxt);//如果词典里有nxt则移除
                        tmp.add(nxt);
                    }
                    //还原被替换的字母
                    a[i] = orig;
                }
            }
            q1 = tmp;//更新beginwords
            dist++;
        }
        return 0;
    }
}
