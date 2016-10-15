import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Author: zhangxin
 * Time: 2016/10/15 0015.
 * Desc:
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
 * Note: The solution set must not contain duplicate subsets.
 * For example,
 * If nums = [1,2,2], a solution is:
 * [
 * []
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * ]
 */
public class SubsetsII {
    //传入的数据是无序的,这样排序的话就会出现重复数据了,因此需要先排序;这个版本没有排序;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Integer> temp;

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int index = map.get(nums[i]);
                int count = lists.size();
                for (int j = index; j < count; j++) {
                    temp = new ArrayList<Integer>();
                    temp.addAll(lists.get(j));
                    temp.add(nums[i]);
                    lists.add(temp);
                }
                map.put(nums[i], lists.size() - 1); //加进来,下次再有重复的再用
            } else {  //不包含
                map.put(nums[i], lists.size());
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(nums[i]);
                lists.add(list); // 新的元素,先添加进去;

                int count = lists.size();

                for (int j = 0; j < count - 1; j++) {
                    temp = new ArrayList<Integer>();
                    temp.addAll(lists.get(j));
                    temp.add(nums[i]);
                    lists.add(temp);
                }
            }
        }
        lists.add(new ArrayList<Integer>());
        return lists;
    }


    /*
    * 可行的方法
    * 思路是:
    * 先将数组排序;
    * 遍历该数组,使用HashMap key用来存储 nums[i],value存储在 lists 出现的位置
    * 如果nums[i]未出现过,那么先将其单独作为一个list,存入 lists 中,然后然和 lists 中之前的 list 合并,添加到 lists中
    * 如果nums[i]出现过,那么和之前 list 合并的工作就已经做过了,但是也需要合并,合并的起始位置就不是从0开始了,而是在map中该元素的value位置开始
    * */
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        // lists.add(new ArrayList<Integer>());

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Integer> temp;

        int count;

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {  //包含
                count = lists.size();
                int index = map.get(nums[i]);
                map.put(nums[i], count);
                for (int j = index; j < count; j++) {
                    temp = new ArrayList<Integer>();
                    temp.addAll(lists.get(j));
                    temp.add(nums[i]);
                    lists.add(temp);
                }
            } else {  //不包含
                //先把自己添加进来
                temp = new ArrayList<Integer>();
                temp.add(nums[i]);
                lists.add(temp);
                map.put(nums[i], lists.size() - 1);

                count = lists.size();
                for (int j = 0; j < count - 1; j++) {
                    temp = new ArrayList<Integer>();
                    temp.addAll(lists.get(j));
                    temp.add(nums[i]);
                    lists.add(temp);
                }
            }
        }
        lists.add(new ArrayList<Integer>());
        return lists;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 5, 5, 5};
        System.out.println(new SubsetsII().subsetsWithDup2(nums));
    }
}
