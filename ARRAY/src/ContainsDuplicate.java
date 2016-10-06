import java.util.*;

/**
 * Created by zhangxin on 2016/10/5 0005.
 * Given an array of integers, find if the array contains any duplicates.
 * Your function should return true if any value appears at least twice in the array,
 * and it should return false if every element is distinct.
 * <p/>
 * 初级解决思路:使用hashMap,但是会超时!!!
 * 第二次尝试:使用hashSet,依旧超时
 * 第三次尝试:使用Array.sort()排序,提交成功
 * 第四次尝试:使用自己写的快速排序方法
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Integer> list = new ArrayList<Integer>();
        list.contains(1);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return true;
            } else {
                map.put(nums[i], nums[i]);
            }
        }

        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        Set<Integer> appearedNum = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!appearedNum.contains(nums[i])) {
                appearedNum.add(nums[i]);
            } else return true;
        }
        return false;
    }

    public boolean containsDuplicate3(int[] nums) {
        if (nums == null || nums.length <= 1)
            return false;

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++)
            if (nums[i] == nums[i + 1])
                return true;

        return false;
    }

    /**
     * *********************************************************
     */
    public boolean containsDuplicate4(int[] nums) {
        if (nums == null || nums.length <= 1)
            return false;

        sort(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length - 1; i++)
            if (nums[i] == nums[i + 1])
                return true;

        return false;
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

    public static void main(String[] args) {
        int[] nums = {3, 3};
       /* int[] nums = new int[30000];
        for (int i = 0; i < 29999; i++) {
            nums[i] = i;
        }*/
        System.out.println(new ContainsDuplicate().containsDuplicate4(nums));
    }
}
