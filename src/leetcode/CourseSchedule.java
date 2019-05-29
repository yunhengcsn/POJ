package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @BelongsProject: POJ
 * @BelongsPackage: leetcode
 * @Author: csn
 * @Description: 207.Course Schedule ;  Solution : 拓扑排序 or dfs
 */
public class CourseSchedule {

    //bfs + adjacent matrix --> topological sort
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        int[][] edges = new int[numCourses][numCourses];//邻接矩阵
        int[] in = new int[numCourses];//入度

        for(int[] e : prerequisites) {
            int curr = e[0];
            int pre = e[1];
            in[curr]++;
            edges[pre][curr] = 1;
        }
        //bfs
        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {
            if(in[i] == 0) q.offer(i);
        }
        while(!q.isEmpty()) {
            int curr = q.poll();
            cnt++;
            for(int i = 0 ; i < numCourses; i++) {
                if(edges[curr][i] != 0) {
                    if(--in[i] == 0) q.offer(i);
                }
            }
        }

        return cnt == numCourses;
    }

    //dfs to find cycle --> cycle existing means not finish
    HashMap<Integer, Boolean> memo = new HashMap<Integer, Boolean>();//Memorization HashMap for DFS pruning
    public boolean canFinish(int n, int[][] edges) {
        if (edges.length != 0) {
            HashMap<Integer, HashSet<Integer>> neighbors = new HashMap<Integer, HashSet<Integer>>(); // Neighbors of each node
            HashSet<Integer> curPath = new HashSet<Integer>(); // Nodes on the current path
            for (int i = 0; i < edges.length; i++) {// Convert graph presentation from edge list to adjacency list
                if (!neighbors.containsKey(edges[i][1]))
                    neighbors.put(edges[i][1], new HashSet<Integer>());
                neighbors.get(edges[i][1]).add(edges[i][0]);
            }

            for (int a[] : edges) // The graph is possibly not connected, so need to check every node.
                if (hasCycle(neighbors, a[0], -1, curPath))// Use DFS method to check if there's cycle in any curPath
                    return false;
        }
        return true;
    }

    boolean hasCycle(HashMap<Integer, HashSet<Integer>> neighbors, int kid, int parent, HashSet<Integer>curPath) {
        if (memo.containsKey(kid)) return memo.get(kid);
        if (curPath.contains(kid)) return true;// The current node is already in the set of the current path
        if (!neighbors.containsKey(kid)) return false;

        curPath.add(kid);
        for (Integer neighbor : neighbors.get(kid)) {
            boolean hasCycle = hasCycle(neighbors, neighbor, kid, curPath);// DFS
            memo.put(kid, hasCycle);
            if (hasCycle) return true;
        }
        curPath.remove(kid);
        return false;
    }
}
