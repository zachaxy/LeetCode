/**
 * Created by zhangxin on 2016/9/22 0022.
 * <p/>
 * Given an array of integers A and let n to be its length.
 * Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation function" F on A as follow:
 * F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].
 * Calculate the maximum value of F(0), F(1), ..., F(n-1).
 * Note:
 * n is guaranteed to be less than 105.
 * Example:
 * A = [4, 3, 2, 6]
 * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
 * <p/>
 * So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
 * <p/>
 * 按照常规思路,算法题目暴力破解方法肯定是不行的;
 * 于是要找到一种规律来计算,既然例子中给出了一个长度为4的数组,我们可以用F(n)-F(n-1)的方式试一试
 * F(n) = F(n-1) + sum - (A.length) * A[A.length - k];      式(1)
 * sum = A[0] + A[1] + ... + A[length-1];                   式(2)
 * 于是我们可以用式(1)来计算F(n),以代替暴力破解法;
 */
public class RotateFunction {

    //暴力破解法,超时!!!
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

    //递归计算,5ms,可用;
    public int maxRotateFunction2(int[] A) {
        if (A.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int sum = 0, init = 0;

        for (int i = 0; i < A.length; i++)
            sum += A[i];

        for (int j = 0; j < A.length; j++)
            init += j * A[j];

        max = init;

        for (int k = 1; k < A.length; k++) {
            init = init + sum - (A.length) * A[A.length - k];
            max = Math.max(init, max);
        }

        return max;
    }

    public static void main(String[] args) {
        int i = -1 + 4;
        System.out.println(i % 4);
    }
}
