import java.util.HashMap;

/**
 * Created by zhangxin on 2016/10/6 0006.
 */
public class Client {
    public static void main(String[] args) {

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 2);

        int v = map.get(2);
        map.put(2,v++);
        System.out.println(map.get(2));
    }
}
