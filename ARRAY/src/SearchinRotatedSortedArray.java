/**
 * Created by zhangxin on 2016/9/20 0020.
 * madan,上来就假设数组是递增的,++
 */
public class SearchinRotatedSortedArray {

    public int search(int[] nums, int target) {
        int first = 0, last = nums.length;
        while (first != last) {
            int mid = first + (last - first) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[first] <= nums[mid]) {
                if (nums[first] <= target && target < nums[mid])
                    last = mid;
                else
                    first = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[last - 1])
                    first = mid + 1;
                else
                    last = mid;
            }
        }
        return -1;
    }
}
