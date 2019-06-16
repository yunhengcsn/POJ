package others;
/*
 * Description:一个每行都是ip的文本文件,请统计出每个ip一共出现过多少次,
 * 并且根据出现次数从大到小排序，输出到新的文本文件
 * */

/*
*用于测试的数据
*192.168.1.1
1.1.1.1
123.456.678.90
1.2.3.4
1.1.1.1
1.1.1.1
1.1.1.1
192.168.1.1
*/
import java.io.*;
import java.util.*;

public class IPHandler{
    public static void main (String[] args){
        HashMap<String,Integer> map = handler();
        //排序
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(new myComparator());
        //打印
        try {
            PrintStream ps = new PrintStream(new FileOutputStream("src/a.txt"));
            for(HashMap.Entry<String,Integer> e : list) {
                ps.println(e.getKey());
                System.out.println(e.getKey());
            }
        }catch(IOException e) {
            System.out.println("Not Found");
        }
    }
    //读取文件返回map
    private static HashMap<String,Integer> handler() {
        BufferedReader in;
        HashMap<String,Integer> map = new HashMap<>();
        String ip;
        try{
            in = new BufferedReader(new FileReader("src/poj/b.txt"));//注意文件位置路径
            try{
                while((ip = in.readLine()) != null){
                    if(isValid(ip)){
                        if(map.containsKey(ip)){
                            int val = map.get(ip);
                            map.put(ip, val + 1);
                        }
                        else map.put(ip,1);
                    }
                }
            }catch(IOException e){
                System.out.println("IO ERROR");
                e.printStackTrace();
            }
        }catch(FileNotFoundException e){
            System.out.println("Not Found inputfile");
        }
        return map;
    }
    private static boolean isValid(String ip) {
        String[] nums = ip.split("\\.");//.需要转义
        if(4 != nums.length) return false;
        for(String s : nums) {
            int val = Integer.parseInt(s);
            if(!(val >=0 && val <= 255)) return false;
        }
        return true;
    }
}
//比较器
class myComparator implements Comparator<HashMap.Entry<String, Integer>> {
    @Override
    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
        return e2.getValue().compareTo(e1.getValue());
    }
}
