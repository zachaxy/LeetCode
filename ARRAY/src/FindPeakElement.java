/**
 * Created by zhangxin on 2016/10/10 0010.
 * <p/>
 * Description :
 * A peak element is an element that is greater than its neighbors.
 * <p/>
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * <p/>
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * <p/>
 * You may imagine that num[-1] = num[n] = -∞.
 * <p/>
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 */
public class FindPeakElement {
    //所想到的最基本的方法,但是效率较低;
    public int findPeakElement(int[] nums) {
        boolean up = false;
        for (int i = 0; i < nums.length - 1; i++) {
            if (up) {
                if (nums[i] < nums[i + 1]) {
                    up = false;//找到上升的了,找下降的;
                    if (i + 1 == nums.length - 1) {
                        return nums.length - 1;
                    }
                }
                //else if (nums[i] == nums[i + 1]) up = true; //如果连着相等,找上升的;
            } else {
                //找下降的
                if (nums[i] > nums[i + 1]) return i;  //如果找到,返回i
                else if (nums[i] == nums[i + 1]) up = true; //如果连着相等,找上升的;
                    //剩下的情况是还是上升的;那么继续还找下降的;
                else {
                    if (i + 1 == nums.length - 1) return i + 1;
                }
            }
        }
        return 0;
    }

    //和上面的方法效率一样...低...
    public int findPeakElement1(int[] nums) {
        int size = nums.length;
        if (size == 1) return 0;
        else {
            if (nums[0] > nums[1]) return 0;
            if (nums[size - 1] > nums[size - 2]) return size - 1;
            for (int i = 1; i < size - 1; i++) {
                if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) {
                    return i;
                }
            }
        }
        return 0;
    }

    //Σ( ° △ °|||)!, 你咋没想出这么简单的代码,可是效率还是低;这种题如果不一个一个的找,难道还有比 O(n)更快的方法?
    public int findPeakElement2(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }

    //O(logn) time complexity and O(1) space complexity...
    //该方法充分运用了 num[-1] = num[n] = -∞.也就是说两侧都是下降的...
    //而且题目说只要找到一个peak即可... ( ఠൠఠ )ﾉ
    public int findPeakElement3(int[] nums) {
        //corner case
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) { //数组中至少三个元素
            int mid = start + (end - start) / 2;
            //peak
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            }
            //decreasing
            else if (nums[mid] < nums[mid - 1]) {
                end = mid;
            }
            //increaseing : A[mid] > A[mid - 1]
            //lowest : A[mid] < A[mid - 1] && A[mid] < A[mid + 1]
            else {
                start = mid;
            }

        }
        if (nums[start] > nums[end]) {
            return start;
        } else {
            return end;
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2};
        System.out.println(new FindPeakElement().findPeakElement(a));
    }
}
