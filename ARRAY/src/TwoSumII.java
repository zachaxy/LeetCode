/**
 * Created by zhangxin on 2016/9/22 0022.
 * <p/>
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution.
 * Please note that your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution.
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 * <p/>
 * 这里要注意的是返回的两个数的下标是从1开始计算的,所以返回值中两个数组要+1
 * <p/>
 * 既然已经排好序了,本来打算用二分查找的,但是如下的算法更为巧妙!!!
 */
public class TwoSumII {
    public int[] twoSum(int[] numbers, int target) {
        int size = numbers.length;
        int lo = 0;
        int hi = size - 1;
        while (lo < hi) {
            int sum = numbers[lo] + numbers[hi];

            if (sum == target) {
                return new int[]{lo + 1, hi + 1};
            } else if (sum > target) {
                hi--;
            } else {
                lo++;
            }
        }
        return null;
    }
}
