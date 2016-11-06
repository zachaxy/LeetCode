/**
 * Author: zhangxin
 * Time: 2016/11/5 0005.
 * Desc:
 * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.
 * <p>
 * For example:
 * <p>
 * Secret number:  "1807"
 * Friend's guess: "7810"
 * Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
 * Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. In the above example, your function should return "1A3B".
 * <p>
 * Please note that both secret number and friend's guess may contain duplicate digits, for example:
 * <p>
 * Secret number:  "1123"
 * Friend's guess: "0111"
 * In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B".
 * You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
 */
public class BullsAndCows {
    public String getHint(String secret, String guess) {
        int A = 0, B = 0;
        int[] nums1 = new int[10];
        int[] nums2 = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            char c1 = secret.charAt(i);
            char c2 = guess.charAt(i);
            if (c1 == c2) {
                A++;
            } else {
                nums1[c1 - '0']++;
                nums2[c2 - '0']++;
            }

        }

        for (int i = 0; i < nums1.length; i++) {
            B = B + Math.min(nums1[i], nums2[i]);
        }
        return "" + A + "A" + B + "B";
    }

    public static void main(String[] args) {
        System.out.println(new BullsAndCows().getHint("1234", "0111"));
    }
}
