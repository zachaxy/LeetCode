/**
 * Created by zhangxin on 2016/9/20 0020.
 * Follow up for “Remove Duplicates”:
 * What if duplicates are allowed at most twice?
 * <p/>
 * For example,
 * Given sorted array A = [1,1,1,2,2,3],
 * <p/>
 * Your function should return length = 5, and A is now [1,1,2,2,3].
 */
public class RemoveDuplicatesfromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        if (nums.length < 3) {
            return nums.length;  //0,1,2
        }

        int index = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[index - 2])  //比较的时候跟前两个比index-2...
                nums[index++] = nums[i];
        }

        return index;
    }

    public static void main(String[] args) {
       int[] nums = {1,1,2,2,2};
        int i = new RemoveDuplicatesfromSortedArrayII().removeDuplicates(nums);
        System.out.println(i);
    }
}
