import java.util.ArrayList;
import java.util.List;

/**
 * Author: zhangxin
 * Time: 2016/10/12 0012.
 * Desc:
 * Given an index k, return the kth row of the Pascal's triangle.
 * For example, given k = 3,
 * Return [1,3,3,1].
 * <p>
 * Note:
 * Could you optimize your algorithm to use only O(k) extra space?
 * <p>
 * 既然要使用O(k)的控件,那就不能使用从上到下的递推公式了,尝试找一找该行从左到右的规律;
 * X30 = 1 , X31 = X30 * (3 - 1 + 1)/1 = 3 , X32 = X31 * (3 - 2 + 1)/2 = 3 , X33 = X32 * (3 - 3 + 1)/3 = 1;
 * 因此当索引为i时,有如下规律:
 * X0 = 1, X(j+1) = Xj * (i - j + 1) / j ;
 */
public class PascalTriangleII {
    //如下的算法是按照从左到右的规律计算的,当rowIndex较小时不会出问题,但是当rowIndex较大时,会出现最中间的数字为负数;
    //问题出现在计算公式中既有乘法也有除法,既然除法可以整除,就应该先算触发,否则先算乘法再除,可能会在乘法时移除
    //测试用例在rowIndex=30的时候开始通不过,就是因为最中间的数字在想成的过程中整型溢出了,所以要判断传如的rowIndex
    // 如果rowIndex>29,拦截住,将中间值转为long
    //list.add(list.get(i - 1) * (rowIndex - i + 1) / i);
    //list.add((rowIndex - i + 1) / i * list.get(i - 1));//但是这样算也有问题,(rowIndex - i + 1) / i 可能不能整除
    //list.add(list.get(i - 1) / i  * (rowIndex - i + 1));//这样也可能不能整除;
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        if (rowIndex < 30) {
            for (int i = 1; i <= rowIndex / 2; i++) {
                list.add(list.get(i - 1) * (rowIndex - i + 1) / i);
            }
        } else {
            for (int i = 1; i <= rowIndex / 2; i++) {
                long l = (long) list.get(i - 1) * (rowIndex - i + 1);
                int v = (int) (l / i);
                list.add(v);
            }
        }

        for (int i = rowIndex / 2 + 1; i <= rowIndex; i++) {
            list.add(list.get(rowIndex - i));
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new PascalTriangleII().getRow(30));
    }
}
