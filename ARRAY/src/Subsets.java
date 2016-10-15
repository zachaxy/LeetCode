import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: zhangxin
 * Time: 2016/10/15 0015.
 * Desc:
 * Given a set of **distinct** integers, nums, return all possible subsets.
 * Note: The solution set must not contain duplicate subsets.
 * For example,
 * If nums = [1,2,3], a solution is:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        List<Integer> tmp, list;
        for (int i = 0; i < nums.length; i++) {
            //用来添加自身;
            list = new ArrayList<Integer>();
            list.add(nums[i]);
            lists.add(list);
            int count = lists.size();
            for (int j = 0; j < count - 1; j++) {
                tmp = new ArrayList<Integer>(lists.get(j));
                tmp.add(nums[i]);
                lists.add(tmp);
            }
        }
        lists.add(new ArrayList<Integer>());
        return lists;
    }

    public List<List<Integer>> subsets2(int[] nums) {
        //其实也就是 2^nums.length 项
        int max_res = 1 << nums.length;

        List<List<Integer>> answer = new LinkedList<List<Integer>>();
        //外层循环,循环max_res次,其实是从 0 ~ 2^nums.length -1 之间的值进行循环;
        for (int i = 0; i < max_res; i++) {
            List<Integer> subset = new LinkedList<Integer>();
            //内层循环,这里bit就要从当前i中取出每一位;
            for (int bit = 0; bit < nums.length; bit++) {
                if (((i >> bit) & 1) == 1) subset.add(nums[bit]);
            }
            answer.add(subset);
        }

        return answer;
    }

    //can beat 98%; 理解该算法的顺序很重要!!!
    //该方法最终的顺序是这样的:{[],[1],[1,2],[1,2,3],[2],[2,3],[3]}
    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        helper(nums, 0, list, res);
        return res;
    }

    private void helper(int[] nums, int idx, ArrayList<Integer> list, List<List<Integer>> res) {
        res.add(list);
        for (int i = idx; i < nums.length; i++) {
            ArrayList<Integer> _list = (ArrayList) list.clone();
            _list.add(nums[i]);
            helper(nums, i + 1, _list, res);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new Subsets().subsets(nums));
    }
}
