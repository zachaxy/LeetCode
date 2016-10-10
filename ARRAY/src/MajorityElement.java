import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhangxin on 2016/10/10 0010.
 * <p/>
 * Description :
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * <p/>
 * You may assume that the array is non-empty and the majority element always exist in the array.
 */
public class MajorityElement {
    //第一种方法,先排序,再找中间值,属于投机取巧的方法
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    //这样速度极慢,还不如上一个方法...
    public int majorityElement1(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int v;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                v = map.get(nums[i]);
                map.put(nums[i], ++v);
            } else {
                map.put(nums[i], i);
            }
        }

        int key = 0;//= Integer.MIN_VALUE;
        int value = 0;
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            int count = (Integer) entry.getKey();
            int val = (Integer) entry.getValue();
            if (val > value) {
                value = val;
                key = count;
            }
        }
        return key;
    }
}
