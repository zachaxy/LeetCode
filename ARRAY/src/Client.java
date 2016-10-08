/**
 * Created by zhangxin on 2016/10/6 0006.
 */
public class Client {
    public static void main(String[] args) {

        int length = 120;
        System.out.println(length >> 3);
        System.out.println(length >> 6);
        System.out.println((length >> 3) + (length >> 6) + 1);
    }
}
