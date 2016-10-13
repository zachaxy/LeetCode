import java.util.Stack;

/**
 * Author: zhangxin
 * Time: 2016/10/12 0012.
 * Desc:
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * <p>
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 * 额...题目理解有误,连续的数组并不是说数字还连着
 * 解题思路:无论如何该值都是>=0的,==0的情况是[-1,0]
 * 因此我们需要找到偶数个负数,找到后在遇到0之前得到一个最大区间,然后越过0,再向后找一个区间;
 * 这有点类似于栈的括号匹配,只不过这里匹配的是两个负数;考虑用栈???
 */

public class MaximumProductSubarray {
    //错误的思路,把前面的数据提前算了,一个错误的bug是:{2,-5,-2,-4,3};我的结果是20,正确结果应该是24;
    //针对上述错误的改进:将数组逆向再算一遍...
    //真是一个糟糕透顶的设计方法!!!!
    public int maxProduct(int[] nums) {
        return Math.max(zhengxiang(nums), nixiang(nums));
    }

    public int zhengxiang(int[] nums) {
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();
        if (nums[0] >= 0) {
            stack1.push(nums[0]);
        } else {  // <0
            stack1.push(nums[0]);
            stack2.push(nums[0]);
        }

        int sum;
        for (int i = 1; i < nums.length; i++) {

            if (nums[i] > 0) { //正数
                if (stack1.peek() > 0) {
                    stack1.push(stack1.pop() * nums[i]);
                } else {  // -1;0;
                    stack1.push(nums[i]);
                }
            } else if (nums[i] < 0) {    //负数
                if (stack2.isEmpty()) {  //第一个负数
                    stack1.push(nums[i]);
                    stack2.push(nums[i]);
                } else {  //第二个负数
                    stack2.pop(); //清空stack2;
                    sum = nums[i];
                    while (!stack1.isEmpty() && stack1.peek() != 0) {
                        sum *= stack1.pop();
                    }
                    stack1.push(sum);
                }
            } else {  // 0
                stack1.push(0);
                stack2.clear();
            }
        }

        if (stack1.isEmpty()) {
            return stack2.pop();
        } else {
            int max = stack1.pop();
            int tmp;
            while (!stack1.isEmpty()) {
                tmp = stack1.pop();
                if (tmp > max) {
                    max = tmp;
                }
            }
            System.out.println("zhengxiang:" + max);
            return max;
        }
    }

    public int nixiang(int[] nums) {
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();
        if (nums[nums.length - 1] >= 0) {
            stack1.push(nums[nums.length - 1]);
        } else {  // <0
            stack1.push(nums[nums.length - 1]);
            stack2.push(nums[nums.length - 1]);
        }

        int sum;
        for (int i = nums.length - 2; i >= 0; i--) {

            if (nums[i] > 0) { //正数
                if (stack1.peek() > 0) {
                    stack1.push(stack1.pop() * nums[i]);
                } else {  // -1;0;
                    stack1.push(nums[i]);
                }
            } else if (nums[i] < 0) {    //负数
                if (stack2.isEmpty()) {  //第一个负数
                    stack1.push(nums[i]);
                    stack2.push(nums[i]);
                } else {  //第二个负数
                    stack2.pop(); //清空stack2;
                    sum = nums[i];
                    while (!stack1.isEmpty() && stack1.peek() != 0) {
                        sum *= stack1.pop();
                    }
                    stack1.push(sum);
                }
            } else {  // 0
                stack1.push(0);
                stack2.clear();
            }
        }

        if (stack1.isEmpty()) {
            return stack2.pop();
        } else {
            int max = stack1.pop();
            int tmp;
            while (!stack1.isEmpty()) {
                tmp = stack1.pop();
                if (tmp > max) {
                    max = tmp;
                }
            }
            System.out.println("nixiang:" + max);
            return max;
        }
    }

    //一种简单的思路,效率明显提高...
    public int maxProduct2(int[] nums) {
        int max = Integer.MIN_VALUE, product = 1;
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            max = Math.max(product *= nums[i], max);
            if (nums[i] == 0) product = 1;
        }

        product = 1;
        for (int i = len - 1; i >= 0; i--) {
            max = Math.max(product *= nums[i], max);
            if (nums[i] == 0) product = 1;
        }

        return max;
    }

    public static void main(String[] args) {
        //int[] nums = {1, 2, 3, -1, 4, 0, 5, 6, -1, 7, 8, -1, 9};
        int[] nums = {2, -5, -2, -4, 3};
        System.out.println(new MaximumProductSubarray().maxProduct2(nums));
    }
}
