import java.util.HashSet;
import java.util.Set;

/**
 * Author: zhangxin
 * Time: 2016/11/6 0006.
 * Desc:
 * A happy number is a number defined by the following process:
 * Starting with any positive integer, replace the number by the sum of the squares of its digits,
 * and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy numbers.
 * Example: 19 is a happy number
 * <p>
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * <p>
 * 难点在于如果不是 幸福数,如何停止循环;
 * 如果不是幸福数,那么循环时各个位的平方和一定有重复的地方;
 */
public class HappyNumber {
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<Integer>();
        while (n != 1) {
            n = getInt(n);
            System.out.println(n);
            if (!set.add(n)) {
                return false;
            }
        }
        return true;
    }

    int getInt(int i) {
        int sum = 0;
        while (i != 0) {
            sum += Math.pow(i % 10, 2);
            i = i / 10;
        }
        return sum;
    }

    //寻找规律,幸福数到最后不是1,就是7,或者是10,其他都还需要继续算;
    // 7 不可能是两个数的平方;
    // 要凑成1或者10或者100才可以, 3@4=>10;6@8=>100
    //其余的个位数都不可能派生出幸福数!!!
    public boolean isHappy2(int n) {
        System.out.println(n);
        // 1, 7, 10 will be the happy number finally.
        if (n == 1 || n == 7 || n == 10) return true;
        if (n < 10) return false;
        int newN = 0;
        newN += Math.pow(n % 10, 2);
        while (n >= 10) {
            n /= 10;
            newN += Math.pow(n % 10, 2);
        }
        return isHappy2(newN);
    }

    public boolean isHappy3(int n) {
        Set<Integer> inLoop = new HashSet<Integer>();
        int squareSum, remain;
        while (inLoop.add(n)) {
            squareSum = 0;
            while (n > 0) {
                remain = n % 10;
                squareSum += remain * remain;
                n /= 10;
            }
            if (squareSum == 1)
                return true;
            else
                n = squareSum;

        }
        return false;
    }

    public static void main(String[] args) {
        //System.out.println(new HappyNumber().isHappy(18));
        System.out.println(new HappyNumber().isHappy2(18));
    }
}
