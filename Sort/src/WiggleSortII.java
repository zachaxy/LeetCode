/**
 * Author: zhangxin
 * Time: 2016/10/22 0022.
 * Desc:
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * <p>
 * Example:
 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 * <p>
 * Note:
 * You may assume all input has valid answer.
 * <p>
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 */
public class WiggleSortII {
    public void wiggleSort(int[] nums) {
        int i = 0;
        for (; i < nums.length - 2; i = i + 2) {
            int j = i + 1;
            if (nums[i] >= nums[i + 1]) {
                findBigger(nums, i + 1, nums[i]);
            }

            if (nums[j] <= nums[j + 1]) {
                findLitter(nums, j + 1, nums[j]);
            }

        }

        if (nums.length - i == 2 && nums[i] > nums[i + 1]) {
            exch(nums, i, i + 1);
        }

        for (int j = 0; j < nums.length; j++) {
            System.out.println(nums[j]);
        }
    }

    void exch(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    void findBigger(int[] nums, int index, int v) {
        for (int i = index; i < nums.length; i++) {
            if (nums[i] > v) {
                //exch(nums, index - 1, i);
                exch(nums, index, i);
                return;
            }
        }
        //本来是想找一个比nums[i]更大的,但是没找到,所以找一个比 v 小的,然后替换;
        for (int i = index; i < nums.length; i++) {
            if (nums[i] < nums[index - 1]) {
                exch(nums, i, index - 1);
                break;
            }
        }
    }

    void findLitter(int[] nums, int index, int v) {
        for (int i = index; i < nums.length; i++) {
            if (nums[i] < v) {
                exch(nums, index, i);
                return;
            }
        }
        //exch(nums, index - 1, index);
        //想找一个小的,但是发现自己就是当前最小的;前面的更小;
        //findBigger(nums,index,v);//那么现在就找一个大的,替换到当前的属性
        for (int i = index; i < nums.length; i++) {
            if (nums[i] > nums[index - 1]) {
                //找到了i
                exch(nums, index - 2, index - 1);
                exch(nums, index - 1, i);
                break;
            }
        }
    }

    public static void main(String[] args) {
        //int[] nums = {4,5,5,6,7,8};//1, 3, 2, 2, 3, 1
        //int[] nums = {1, 3, 2, 2, 3, 1};
        //int[] nums = {1, 5,1,1,6,4};
        //int[] nums = {4, 5, 5, 5, 5, 6, 6, 6};
        //int[] nums = {1,1,2,2,2,1};
        //int[] nums = {2, 3, 3, 2, 2, 2, 1, 1};
        int[] nums = {2, 1, 1, 1, 1, 3, 2, 2};
        new WiggleSortII().wiggleSort2(nums);
    }

    public void wiggleSort2(int[] nums) {
        int i = 0;
        for (; i < nums.length - 2; i = i + 2) {
            int j = i + 1;
            if (nums[i] >= nums[i + 1]) {
                int l = findLitter2(nums, i + 1, nums[i]);
                if (l > 0) {
                    exch(nums, l, i);
                } else {  //说明nums[i]是最小的;
                    int g = findBigger2(nums, i + 1, nums[i]);
                    exch(nums, i, j);
                    exch(nums, g, j);
                }
            }

            if (nums[j] <= nums[j + 1]) {
                int l = findLitter2(nums, j + 1, nums[j]);
                if (l > 0) {
                    exch(nums, l, j + 1);
                } else {  //说明nums[j]是最小的;
                    int g = findBigger2(nums, j, nums[j]);
                    exch(nums, g, j + 1);

                }
            }

        }

        if (nums.length - i == 2 && nums[i] > nums[i + 1]) {
            exch(nums, i, i + 1);
        }

        for (int j = 0; j < nums.length; j++) {
            System.out.println(nums[j]);
        }
    }

    int findBigger2(int[] nums, int index, int v) {
        int i = index;
        for (; i < nums.length; i++) {
            if (nums[i] > v) {
                //exch(nums, index - 1, i);
                exch(nums, index, i);
                return i;
            }
        }
        return 0;
    }

    int findLitter2(int[] nums, int index, int v) {
        int i = index;
        for (; i < nums.length; i++) {
            if (nums[i] < v) {
                exch(nums, index, i);
                return i;
            }
        }
        return 0;
    }
}
