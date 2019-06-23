package poj;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Description:位压缩+广度优先搜索
 * 0^1=0；1^1=0--->实现翻转
 * */

public class Poj1753 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int origin=0;
        int ep=15;
        for(int m=0;m<4;m++){
            String str=sc.next();
            for(int i=0;i<4;i++){
                origin+=(str.charAt(i)=='b')?(int)Math.pow(2,ep):0;
                ep--;
            }
        }
        int[] helper=init();//生成用于异或翻转棋盘的数组
        int cnt=bfs(origin,helper);
        if(cnt==-1) System.out.println("Impossible");
        else System.out.println(cnt);

    }

    private static class Node{
        int val;
        int step;
        Node(int val,int step){
            this.val=val;
            this.step=step;
        }

    }

    private static int bfs(int num,int[] arr){

        boolean [] visited=new boolean[65536];
        Arrays.fill(visited,false);
        Node curr=new Node(num,0);
        visited[num]=true;
        Queue<Node> q=new LinkedList<>();
        q.add(curr);
        while(!q.isEmpty()){
            Node n=q.poll();
            if(n.val==0||n.val==65535){
                return n.step;
            }
            for(int i=0;i<16;i++){//对于每步的棋盘，有16种变换方式
                int tmp=n.val^arr[i];
                if(visited[tmp]) continue;//已访问过则判断
                visited[tmp]=true;
                if(tmp==0||tmp==65535){
                    return n.step+1;
                }
                q.add(new Node(tmp,n.step+1));
            }

        }
        return -1;
    }

    private static int[] init(){
        int[] arr=new int[16];
        int id=0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                int tmp;
                tmp=1<<((3-i)*4+3-j);//第0行处于高位，第0列处于高位，1左移(3-i)*4+3-j位
                //四周
                if(i+1<4) tmp^=1<<((3-i-1)*4+3-j);
                if(j+1<4) tmp^=1<<((3-i)*4+3-j-1);
                if(i-1>=0) tmp^=1<<((3-i+1)*4+3-j);
                if(j-1>=0) tmp^=1<<((3-i)*4+3-j+1);
                arr[id++]=tmp;
            }
        }
        return arr;
    }
}
