package leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
/*675. Cut Off Trees for Golf Event
* You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:
* 0 represents the obstacle can't be reached.
* 1 represents the ground can be walked through.
* The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
* You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first.
* And after cutting, the original place has the tree will become a grass (value 1).
* You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees.
* If you can't cut off all the trees, output -1 in that situation.
* You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
* Example 1:Input: [ [1,2,3], [0,0,4], [7,6,5]] Output: 6
* Example 2:Input: [ [1,2,3], [0,0,0], [7,6,5]] Output: -1
* Example 3:Input: [ [2,3,4], [0,0,5], [8,7,6]] Output: 6
* Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
*
* Solution: pq + bfs
* */
public class CutOffTreesforGolfEvent {
    private int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};//上下左右坐标
    class Node {
        int h;//the height of the tree
        int r;//coordinate of row
        int c;//coordinate of col

        Node(int h,int r,int c) {
            this.h = h;
            this.r = r;
            this.c = c;

        }
        Node(int r,int c) {
            this.r = r;
            this.c = c;
        }
    }
    public int cutOffTree(List<List<Integer>> forest) {
        PriorityQueue<Node> pq = new PriorityQueue<>((Node n1, Node n2)->(n1.h-n2.h));
        int rowsNum = forest.size();
        int colNum = forest.get(0).size();
        //按照树的高度排序
        for(int i = 0;i < rowsNum;i++){
            List<Integer> row = forest.get(i);
            for(int j = 0;j < colNum;j++) {
                int val = row.get(j);
                if(val != 0) {
                    Node node = new Node(val,i,j);
                    pq.offer(node);
                }
            }
        }
        int ans = 0;
        int d,prer=0,prec=0;
        //更新距离
        while(!pq.isEmpty()) {
            Node node = pq.poll();//弹出当前最矮的树高
            d =dists(prer,prec,node.r,node.c,rowsNum,colNum,forest);//计算该树坐标到上一棵坐标的移动距离
            if(d == -1) return -1;//不可达则return
            ans += d;
            //更新上一棵树的坐标
            prer = node.r;
            prec = node.c;

        }
        return ans;
    }
    //bfs计算距离
    private int dists(int pr,int pc,int r, int c,int rNum,int cNum,List<List<Integer>> forest) {
        //坐标相同直接return 0
        if(pr == r && pc == c) return 0;
        Queue<Node> q = new LinkedList<>();
        //是否已访问访问
        boolean[][] visited = new boolean[rNum][cNum];
        Node s = new Node(pr,pc);
        q.offer(s);
        int dist = 0;//距离
        visited[pr][pc] = true;
        //遍历更新
        while(!q.isEmpty()) {
            int size = q.size();
            dist++;
            for(int cnt = 0;cnt < size;cnt++) {
                Node tmp = q.poll();
                int i = tmp.r;
                int j = tmp.c;
                //上下左右4个点更新
                for(int k = 0; k<4; k++){
                    int x = i + dir[k][0], y = j + dir[k][1];
                    if(x>=0 && x<rNum && y>=0 && y<cNum && forest.get(x).get(y) > 0 && !visited[x][y] ){
                        if(x == r && y == c)return dist;
                        visited[x][y] = true;
                        q.offer(new Node(x,y));
                    }
                }
            }
        }
        return -1;

    }
}
