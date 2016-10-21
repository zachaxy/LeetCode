/**
 * Author: zhangxin
 * Time: 2016/10/21.
 * Desc:
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * <p>
 * For example,
 * s = "anagram", t = "nagaram", return true.
 * s = "rat", t = "car", return false.
 * 只有小写字母
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        //只是添加了下面一行,从20%提升到50%,注意细节;
        if(s.length() != t.length()){ return false;}
        int[] is = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            is[c-97]++;
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            is[c-97]--;
        }
        for (int i = 0; i < is.length; i++) {
            if(is[i]!=0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new ValidAnagram().isAnagram("aeel","eeal"));
    }
}
