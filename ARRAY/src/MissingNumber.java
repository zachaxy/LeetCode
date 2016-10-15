/**
 * Author: zhangxin
 * Time: 2016/10/15 0015.
 * Desc:
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * <p>
 * For example,
 * Given nums = [0, 1, 3] return 2.
 * <p>
 * 不用排序,直接全部加起来,和实际的 (1+n)*n/2 做差,直接返回;
 */
public class MissingNumber {
    //使用求和再做差的方法;但是可能存在的问题是如果n过大,可能会出现overflow;
    public int missingNumber(int[] nums) {
        int sum = 0;
        int count = nums.length;
        for (int i = 0; i < count; i++) {
            sum += nums[i];
        }
        return (1 + count) * count / 2 - sum;
    }

    //针对上述方法,采用 位运算的方法,思路一样,但是可以避免 整数溢出;
    public int missingNumber2(int[] nums) {
        int all = 0;
        for (int i = 0; i <= nums.length; i++) {
            all ^= i;
        }

        int arrResult = 0;
        for (int i = 0; i < nums.length; i++) {
            arrResult ^= nums[i];
        }

        return all ^ arrResult;
    }
}
