/**
 * Created by zhangxin on 2016/9/20 0020.
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        if(nums.length == 0)  return 0;
        int index = 0; //index表示的是数组中最后一个不等于val的位置
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] != val) {
                index = i;
                if (index == 0) {
                    if (nums[0] != val) {
                        return 1;
                    }
                    return 0;
                }
                System.out.println(index);
                break;
            }
        }

        if (index == 0) {
            if (nums[0] != val) {
                return 1;
            }
            return 0;

        }

        for (int i = 0; i <= index; i++) {

            if (nums[i] == val) {
                nums[i] = nums[index];
                System.out.println(i+"<>"+index);
                for (int j = index - 1; j >=0; j--) {
                    if (nums[j] != val) {
                        index = j;
                        System.out.println("in----"+index);
                        break;
                    }
                }
            }

        }


        return index + 1;
    }

    public int func(int[] nums, int val){
        int index = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != val) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] nums = {4,5};
        int i = new RemoveElement().removeElement(nums,4);
        System.out.println("--" + i);
    }
}
