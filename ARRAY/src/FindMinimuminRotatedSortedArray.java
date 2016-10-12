/**
 * Author: zhangxin.
 * Time:   2016/10/10 0010.
 * Description :
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p>
 * Find the minimum element.
 * <p>
 * You may assume no duplicate exists in the array.
 * <p>
 * 额...工程优化的折半查找法???,不过目前的运行效率还是很低;
 */
public class FindMinimuminRotatedSortedArray {
    public int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];

        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start < end - 1) {
            mid = (start + end) / 2;
            if (nums[start] < nums[mid] && nums[mid] > nums[end]) {
                start = mid;
            } else if (nums[start] > nums[mid] && nums[mid] < nums[end]) {
                end = mid;
            } else if (nums[start] < nums[mid] && nums[mid] < nums[end]) {
                return nums[start];
            }
        }
        return Math.min(nums[start], nums[end]);
    }

    public static void main(String[] args) {
        //int[] a = {4, 5, 6, 7, 8, 9, 0, 1, 2, 3,};
        int[] a = {4, 5, 6, 1};
        System.out.println(new FindMinimuminRotatedSortedArray().findMin(a));
    }
}
