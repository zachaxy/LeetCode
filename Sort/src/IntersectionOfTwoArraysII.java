import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: zhangxin
 * Time: 2016/10/21.
 * Desc:
 * Given two arrays, write a function to compute their intersection.
 * <p>
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 * <p>
 * Note:
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * Follow up:
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */


public class IntersectionOfTwoArraysII {
    //总耗时8ms;该方法中一个蹩脚的问题是,不知道公共元素有多少个,所以也无法确定数组个数,只能先用list,然后在转换
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<Integer>();
        int i = 0, j = 0;
        int len1 = nums1.length;
        int len2 = nums2.length;
        while (i < len1 && j < len2) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                if (nums1[i] > nums2[len2 - 1]) break; //(1)
                j++;
            } else {
                if (nums2[j] > nums1[len1 - 1]) break; //(2)
                i++;
            }
        }
        int[] result = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            result[k] = list.get(k);
        }

        return result;
    }

    //添加了(1)(2)的判断后,从8ms替身到了5ms

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};

        int[] r = new IntersectionOfTwoArraysII().intersect(nums1, nums2);
        for (int k = 0; k < r.length; k++) {
            System.out.println(r[k]);
        }
    }
}
