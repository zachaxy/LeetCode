/**
 * Created by zhangxin on 2016/10/10 0010.
 * <p/>
 * Description :
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 */
public class SortColors {
    public void sortColors(int[] nums) {
        int[] a = new int[3];
        for (int i = 0; i < nums.length; i++) {
            a[nums[i]]++;
        }
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < a[i]; j++) {
                nums[k++] = i;
            }
        }
    }
}
