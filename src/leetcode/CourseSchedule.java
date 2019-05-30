package leetcode;

import java.util.*;

/**
 * @BelongsProject: POJ
 * @BelongsPackage: leetcode
 * @Author: csn
 * @Description: 207.Course Schedule ;  Solution : 拓扑排序 or dfs
 */
public class CourseSchedule {

    //1.bfs + adjacent matrix --> topological sort
    /*
     * Description: The basic idea is to use Topological algorithm: put NODE with 0 indgree into the queue,
     * then make indegree of NODE's successor dereasing 1. Keep the above steps with BFS.Finally, if each node' indgree equals 0,
     * then it is validated DAG (Directed Acyclic Graph), which means the course schedule can be finished.
     */
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites.length == 0) return true;

        // Convert graph presentation from edges to indegree of adjacent list.
        int[] indegree = new int[numCourses];
        for (int[] ints : prerequisites) indegree[ints[0]]++;

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            if (indegree[i] == 0)
                queue.add(i);

        // How many courses don't need prerequisites.
        int canFinishCount = 0;
        while (!queue.isEmpty()) {
            int prerequisite = queue.remove(); // Already finished this prerequisite course.
            canFinishCount++;
            for (int[] ints : prerequisites) {
                if (ints[1] == prerequisite) {
                    if (--indegree[ints[0]] == 0) {
                        queue.offer(ints[0]);
                    }
                }
            }
        }

        return (canFinishCount == numCourses);
    }

    //2.dfs to find cycle --> cycle existing means not finish
    /*
     * Description: The basic idea is using DFS to check if the current node was already included in the traveling path.
     * In this case, we need to convert graph presentation from edge list to adjacency list first, and then check if there's cycle existing in any subset.
     * Because tree is a connected graph, we can start from any node.The graph is possibly not connected, so need to check every node.
     * To do memorization and pruning, a HashMap is used to save the previous results for the specific node.
     */
    private HashMap<Integer, Boolean> memo = new HashMap<>();//Memorization HashMap for DFS pruning
    public boolean canFinish2(int n, int[][] edges) {
        if (edges.length != 0) {
            HashMap<Integer, HashSet<Integer>> neighbors = new HashMap<>(); // Neighbors of each node
            HashSet<Integer> curPath = new HashSet<>(); // Nodes on the current path
            for (int[] edge : edges) {// Convert graph presentation from edge list to adjacency list
                if (!neighbors.containsKey(edge[1]))
                    neighbors.put(edge[1], new HashSet<>());
                neighbors.get(edge[1]).add(edge[0]);
            }

            for (int[] a : edges) // The graph is possibly not connected, so need to check every node.
                if (hasCycle(neighbors, a[0], -1, curPath))// Use DFS method to check if there's cycle in any curPath
                    return false;
        }
        return true;
    }

    private boolean hasCycle(HashMap<Integer, HashSet<Integer>> neighbors, int kid, int parent, HashSet<Integer> curPath) {
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


    //3.dfs:great performance
    private ArrayList<Integer>[] G;
    private int[] vis; // 0: not visited, 1 : visited, 2 : visiting

    // topological sorting should be used on directed acyclic graph
    public boolean canFinish3(int numCourses, int[][] prerequisites) {
        G = new ArrayList[numCourses];
        vis = new int[numCourses];
        for(int i = 0; i < numCourses; i++)
            G[i] = new ArrayList<>();
        for (int[] prerequisite : prerequisites) {
            int to = prerequisite[0];
            int from = prerequisite[1];
            G[from].add(to);
        }
        for(int i = 0; i < numCourses; i++)
            if(!dfs(i))
                return false;
        return true;
    }
    private boolean dfs(int cur){
        if(vis[cur] == 1)
            return true;
        if(vis[cur] == 2)
            return false;
        vis[cur] = 2; // visiting
        for(int to : G[cur]){
            if(!dfs(to))
                return false;
        }
        vis[cur] = 1; // visited
        return true;
    }
}
