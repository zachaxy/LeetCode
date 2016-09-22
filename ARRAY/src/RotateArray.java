/**
 * Created by zhangxin on 2016/9/22 0022.
 */
public class RotateArray {
    public void rotate(int[] nums, int k) {
        if (k == nums.length || k <= 0) return;

        int size = nums.length;
        k = k % size;
        int[] tmp = new int[k];
        for (int i = 0; i < k; i++) {
            tmp[i] = nums[size - k + i];
        }
        show("tmp", tmp);

        for (int i = size - 1; i >= k; i--) {
            nums[i] = nums[i - k];
        }

        for (int i = 0; i < k; i++) {
            nums[i] = tmp[i];
        }
    }

    void show(String s, int[] tmp) {
        System.out.println("*****" + s + "*******");
        for (int i = 0; i < tmp.length; i++) {
            System.out.print(tmp[i] + "  ");
        }
        System.out.println("\n************");
    }

    static int[] nums = {1, 2, 3};
    static int k = 2;

    public static void main(String[] args) {
        new RotateArray().rotate(nums, k);

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
