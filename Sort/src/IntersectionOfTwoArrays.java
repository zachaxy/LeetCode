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
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 * <p>
 * Note:
 * Each element in the result must be unique.
 * The result can be in any order.
 */
public class IntersectionOfTwoArrays {
    //beat 92%,这个还算效率高的???
    public int[] intersection(int[] nums1, int[] nums2) {
        //首先将两个数组排序;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<Integer>();
        int i = 0, j = 0;
        int len1 = nums1.length;
        int len2 = nums2.length;
        while (i < len1 && j < len2) {
            if (nums1[i] == nums2[j]) {
                int target = nums1[i];
                list.add(target);
                while (nums1[i] == target) {
                    i++;
                    if (i == len1) {
                        break;
                    }
                }
                while (nums2[j] == target) {
                    j++;
                    if (j == len2) {
                        break;
                    }
                }
                //此时的 i/j都不等于 target
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }
        int[] result = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            result[k] = list.get(k);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};

        int[] r = new IntersectionOfTwoArrays().intersection(nums1, nums2);
        for (int k = 0; k < r.length; k++) {
            System.out.println(r[k]);
        }
    }
}
