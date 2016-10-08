import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by zhangxin on 2016/10/8 0008.
 * <p/>
 * Description :
 * Given an array of integers and an integer k,
 * find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j]
 * and the difference between i and j is at most k.
 */
public class ContainsDuplicateII {

    //最先想到的就是这种方法,效率好像不高啊
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if ((i - map.get(nums[i])) <= k) {
                    return true;
                } else {
                    map.remove(nums[i]);
                    map.put(nums[i], i);
                }
            } else {
                map.put(nums[i], i);
            }
        }
        return false;
    }

    //不错的思路!!!
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if(nums.length==0 || k==0){
            return false;
        }

        HashSet<Integer> hs = new HashSet<Integer>();
        for(int i = 0; i<nums.length; i++){
            if(i>k) hs.remove(nums[i-k-1]);
            if(hs.contains(nums[i])) return true;
            else hs.add(nums[i]);

        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,4,8,9};
        System.out.println(new ContainsDuplicateII().containsNearbyDuplicate(a,5));
    }

}
