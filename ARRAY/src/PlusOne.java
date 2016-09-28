/**
 * Created by zhangxin on 2016/9/28 0028.
 * Given a non-negative number represented as an array of digits, plus one to the number.
 * <p/>
 * The digits are stored such that the most significant digit is at the head of the list.
 * <p/>
 * 最高为的在数组前面,digits[0]是最高位的数字
 *
 * 存在两种情况:
 * 一种全为9,那么数组肯定要生成一个新的,比之前多一位,且为10000...
 * 其余的,只要不全是9,数组就不用变,从低位向高位遍历,只要低位等于9,将其置为0,直到遇到一位不为9,将该位+1,然后返回;
 *
 * 提交结果:
 * runtime=0ms,beat 38%,说明还有很大提升,仍需探索;
 */
public class PlusOne {

    //该方法效率太慢,
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        //如果数组中全是9,那么就要多加一位了;
        int[] newDigist = new int[digits.length + 1];
        newDigist[0] = 1;
        /*for (int i = 1; i < newDigist.length; i++) {
            newDigist[i] = 0;
        }*/
        return newDigist;
    }
}
