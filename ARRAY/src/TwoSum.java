import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zhangxin on 2016/9/22 0022.
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution.
 * 坑:该数组是无序的,而结果是要返回遇原数组的索引.
 *
 * 解决方法:之前考虑的方法是先将元数组的每个元素对应的下标存入hashmap中
 *
 * 最优解:创建一个HashMap(读取速度快),遍历所给数组的元素,查看其是map中是否存在,初始化的map里面不包含内容,所以把target - v存入数组中
 * 也就是要找的另一半存入数组中,将其值设置为当前索引;
 * 接着遍历,找到另一半存在数组中直接返回
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] result = new int[2];

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int index = map.get(nums[i]);
                result[0] = index ;
                result[1] = i;
                break;
            } else {
                map.put(target - nums[i], i);
            }
        }

        return result;
    }
}
