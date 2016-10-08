/**
 * Created by zhangxin on 2016/10/8 0008.
 * <p/>
 * Description :
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * <p/>
 * Note:
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * <p/>
 * <p/>
 * 常规的思路是拷贝一个nums1的副本,然后在merge进nums1中,但是既然nums1长度足够,那么何不倒着排;
 */
public class MergeSortedArray {

    //效率奇差!!!
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        int index1 = m - 1;
        int index2 = n - 1;
        int index = m + n - 1;
        System.out.println(index);
        while (index >= 0) {
            if (index2 == -1) break;
            if (index1==-1){
                for (int i = 0; i <= index2; i++) {
                    nums1[i] = nums2[i];
                }
                break;
            }
            if (nums1[index1] <= nums2[index2]) {
                nums1[index] = nums2[index2];
                index--;
                index2--;
            } else {
                nums1[index] = nums1[index1];
                index--;
                index1--;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        int[] b = new int[3];
        a[0] = 2;
        b[0] = 1;

        new MergeSortedArray().merge(a,1,b,1);
    }
}
