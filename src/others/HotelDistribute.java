package others;
/*
  Description:
  在飞猪上面有千万级酒店售卖，一般飞猪小二会辅助运营酒店，
  假设目前有9个城市的酒店由3个小二来运营，
  以杭州向北京辐射的话，那么杭州、湖州、上海归运营小二A,南京、济南归运营小二B,石家庄、郑州、天津、北京归运营小二C,
  为了方便运营管理，会让地理位置较近的城市归属一个小二来运营，可以理解城市是一个序列( 1 -  n)，
  小二A可以运营 1、2、3，但是不能运营1、2、4，一般一个城市只能由一个飞猪小二运营，由于每个城市酒店的数量不一致，工作的强度差异较大，
  可能由于分配不均衡导致有些酒店管理不到，所以需要将飞猪小二的工作分配更加均衡，方差最小，
  如果有n个城市,k个运营小二，请帮运营主管设计一套最均衡的分配方案吧。告诉主管k个小二需要运营的城市个数,
  并且用列表展示出来,用H1,...,Hn表示各城市的酒店数量。
  例子1:
  输入：
      9
      3
      1, 2, 3, 1, 1, 3, 2, 3, 4
  输出:   3;4;2;   (每个小二之间用分号隔开)
  说明:
   1+2+3 =6      小二A需要运营的酒店,3个城市
   1+1+3+2 =7  小二B需要运营的酒店,4个城市
   3+4  =7         小二C需要运营的酒店,2个城市
  注意:在分配过程中，运营主管有一项要求是,城市分配可前可后的情况下，优先分配给后者小二，便于能力强的小二承担多些，
  例如例子改成： 1+2+3+1 =7，1+3+2 =6，3+4 =7，是不行的，均衡来说 6、7、7和7、6、7没区别，就主管要求来说，6、7、7是正确的

  Tip:离散型随机变量方差计算公式： * D（X）=E{[X-E（X）]^2}=E（X^2） - [ E（X）]^2;深度优先搜索//+剪枝（动态规划）
  */

import java.util.*;

public class HotelDistribute {
    //
    private static String citys(int n, int k, int[] hotels) {
        ArrayList<Integer> list = new ArrayList<>(k);//存储每次分割的起始位置
        ArrayList<Integer> minList = new ArrayList<>(k);
        helper(hotels,k-1,0,minList,list,0);//k个小二切分k-1次
        //输出
        StringBuilder sb=new StringBuilder();
        for( int i = 0;i < minList.size()-1;++i) {
            sb.append(minList.get(i));
            sb.append(";");
        }
        return sb.toString();
    }
    private static void helper(int[] hotels,int remain, int st, ArrayList<Integer> minList,
                       ArrayList<Integer> list,int sum) {
        int len=hotels.length;
        //最后一次更新最小和
        if(remain==0) {
            int segsum=0;
            for(int j = st;j < len; ++j) {
                segsum += hotels[j] ;
            }
            sum += segsum*segsum;
            list.add(len-st);
            if(minList.size() == 0 || minList.get(minList.size()-1) > sum) {
                minList.clear();
                minList.addAll(list);
                minList.add(sum);
            }
            list.remove(list.size()-1);
            return;
        }
        //递归
        for(int i = st;i <= len-1-remain;++i) {
            list.add(i - st + 1);
            int segsum = 0;
            for (int j = st; j <= i; ++j) {
                segsum += hotels[j];
            }
            sum += segsum * segsum;
            helper(hotels, remain - 1, i + 1, minList, list, sum);
            sum -= segsum * segsum;
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String res;

        int _n;
        _n = Integer.parseInt(in.nextLine().trim());

        int _k;
        _k = Integer.parseInt(in.nextLine().trim());


        String hotelsStr = in.nextLine().trim();
        String[] hotelArray = hotelsStr.split(",");
        int[] _hotels = new int[hotelArray.length];

        for(int _hotels_i = 0; _hotels_i < hotelArray.length; _hotels_i++) {
            _hotels[_hotels_i] = Integer.parseInt(hotelArray[_hotels_i].trim());
        }

        res = citys(_n, _k, _hotels);
        System.out.println(res);
    }
}

