/**
 * Created by zhangxin on 2016/9/22 0022.
 */
public class RotateFunction {
    public int maxRotateFunction(int[] A) {
        if (A.length == 0) return 0;

        int maxSum = 0;
        int size = A.length;
        int sum;
        //这个i改变的是索引,而不是数组的下标;
        for (int i = 0; i < size; i++) {
            sum = 0;
            for (int j = 0; j < size; j++) {
                sum += j * A[(size - i + j) % size];
            }
            if (i == 0) {
                maxSum = sum;
            } else if (sum > maxSum) {
                maxSum = sum;
            }
        }

        return maxSum;
    }
}
