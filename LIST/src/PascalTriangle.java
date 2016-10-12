import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxin on 2016/10/12 0012.
 * Given numRows, generate the first numRows of Pascal's triangle.
 * <p>
 * For example, given numRows = 5,
 * Return
 * <p>
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 */
public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        if (numRows < 0) return null;
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        if (numRows == 0) return lists;
        List<Integer> list;
        if (numRows == 1) {
            list = new ArrayList<Integer>();
            list.add(1);
            lists.add(list);
            return lists;
        } else {
            //numRows>1,先把第二行初始化后面要用
            list = new ArrayList<Integer>();
            list.add(1);
            lists.add(list);
            list = new ArrayList<Integer>();
            list.add(1);
            list.add(1);
            lists.add(list);

            List<Integer> listPro;
            for (int i = 2; i < numRows; i++) {
                System.out.println(i);
                list = new ArrayList<Integer>();
                //先把前两个初始化好
                list.add(1);
                list.add(i);
                listPro = lists.get(i - 1);
                for (int j = 2; j <= i / 2; j++) {
                    list.add(listPro.get(j - 1) + listPro.get(j));
                }
                for (int j = i / 2 + 1; j <= i; j++) {
                    list.add(list.get(i - j));
                }
                lists.add(list);

                /*else {
                    for (int j = 0; j <= i/2 ; j++) {

                    }
                }*/
            }
            return lists;
        }

    }

    public static void main(String[] args) {
        new PascalTriangle().generate(5);
    }
}
