import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxin on 2016/9/28 0028.
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * <p/>
 * Note: The solution set must not contain duplicate triplets.
 * <p/>
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * <p/>
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p/>
 * 初步思路:先排序;遍历->剩余元素相加等于当前遍历元素的相反数,运行时间还是比较长的,还需要改进;
 * 该思路解题中遇到的坑:首先lists不能有重复list,(之前想过用list.contains()方法来判断是否已经存在,不存在再add,但是超时!)出现重复list的情况有两种:
 * 第一种情况:三个数的第一个数字是重复的,也就是说在选择第一个元素的时候,数组中(已经排好序)接连几个都是相同的,要避免这种情况,使用33行的处理
 * 第二种情况:三个数的第二个数字是重读的,也就是说在选择第二个元素的时候,数组中(已经排好序)接连几个都是相同的,要避免这种情况,使用87行的处理
 */
public class Sum3 {
    List<List<Integer>> lists = new ArrayList<List<Integer>>();

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<List<Integer>> tmp;
        sort(nums, 0, nums.length - 1);//至此,真个nums现在已经有序了;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            findT(nums, i + 1, -nums[i]);
        }
        return lists;
    }

    void sort(int[] nums, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(nums, lo, hi);
        sort(nums, lo, j - 1);
        sort(nums, j + 1, hi);
    }

    //partition算法:
    public int partition(int[] nums, int lo, int hi) {
        //变量初始化:
        int i = lo;
        int j = hi + 1;
        int v = nums[lo];

        while (true) {
            //开始找两个元素进行交换!!!
            while (nums[++i] < v) {
                if (i == hi) { //v是最大元素
                    break;
                }
            }//至此,已经找到了一个元素比v大

            while (nums[--j] > v) {
                if (j == lo) { //v是最小元素,与上一步不可能同时满足,v既是最大元素又是最小元素;
                    break;
                }
            }//至此,已经找到一个元素比v小

            if (i >= j) {
                break; //跳出整个循环,这时候已经区分好
            } else {    //找到两个元素,交换!!!循环继续;
                exch(nums, i, j);
            }
        }//至此,已经切分后了,并且i>=j;所以j所在的位置,一定是一个小于v的位置,否则while(true)内部第一个子while不会停止;
        exch(nums, lo, j);
        return j;
    }

    void exch(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    void findT(int[] nums, int start, int target) {
        int i = start;
        int j = nums.length - 1;
        int sum;
        while (i < j) {
            if (i > start && nums[i] == nums[i - 1]) {
                i++;
                continue;
            }
            sum = nums[i] + nums[j];
            if (sum == target) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(-target);
                list.add(nums[i]);
                list.add(nums[j]);
                lists.add(list);
                i++;
                j--;
            } else if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 0, 2, 2};
        List<List<Integer>> lists = new Sum3().threeSum(nums);
        System.out.println(lists);
    }
}
