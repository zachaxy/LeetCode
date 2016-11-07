import java.util.HashMap;

/**
 * Author: zhangxin
 * Time: 2016/11/5 0005.
 * Desc: 最长回文字符串的长度;
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
 * <p>
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * <p>
 * Note:
 * Assume the length of given string will not exceed 1,010.
 * <p>
 * Example:
 * <p>
 * Input:
 * "abccccdd"
 * <p>
 * Output:
 * 7
 * <p>
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {

        //27ms
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                int count = map.get(c);
                map.put(c, count + 1);
            } else {
                map.put(c, 1);
            }
        }

        int sum = 0;
        int max = 0;
        int detal = 0;
        java.util.Collection<Integer> c = map.values();
        for (Integer i : c) {
            if (i % 2 == 0) {
                sum += i;
            } else {
                detal = 1;
                sum = sum + i - 1;
            }
        }
        return sum + max + detal;
    }

    // 15ms
    public int longestPalindrome2(String s) {
        int[] l = new int[26];
        int[] g = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a') {
                l[c - 'a']++;
            } else {
                g[c - 'A']++;
            }
        }
        int detal = 0;
        int sum = 0;
        for (int i = 0; i < l.length; i++) {
            sum += l[i];
            if (sum % 2 == 1) {
                sum--;
                detal = 1;
            }
        }
        for (int i = 0; i < g.length; i++) {
            sum += g[i];
            if (sum % 2 == 1) {
                sum--;
                detal = 1;
            }
        }
        return sum + detal;
    }

    // 11ms
    public int longestPalindrome3(String s) {
        int[] freq = new int[256];
        int count = 0;
        for (char ch : s.toCharArray()) {
            freq[ch]++;
            if (freq[ch] == 2) {
                count += 2;
                freq[ch] = 0;
            }
        }
        if (count < s.length()) count += 1;
        return count;
    }

    public static void main(String[] args) {
        String s = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
        System.out.println(s.length());
        System.out.println(new LongestPalindrome().longestPalindrome(s));
    }
}
