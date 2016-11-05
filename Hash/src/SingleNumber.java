import java.util.HashSet;
import java.util.Iterator;

/**
 * Author: zhangxin
 * Time: 2016/11/5 0005.
 * Desc:
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * <p>
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class SingleNumber {
    // run 19ms,速度较慢;
    public int singleNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();

        for (int i : nums) {
            if (!set.add(i)) {
                set.remove(i);
            }
        }
        Iterator<Integer> iterator = set.iterator();
        return iterator.next();
    }

    // 不申请额外的空间,使用 抑或操作符,run 1ms;
    public int singleNumber2(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }
}
