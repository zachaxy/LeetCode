import java.util.HashMap;
import java.util.HashSet;

/**
 * Author: zhangxin
 * Time: 2016/11/5 0005.
 * Desc:
 * Given a pattern and a string str, find if str follows the same pattern.
 * <p>
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * <p>
 * Examples:
 * pattern = "abba", str = "dog cat cat dog" should return true.
 * pattern = "abba", str = "dog cat cat fish" should return false.
 * pattern = "aaaa", str = "dog cat cat dog" should return false.
 * pattern = "abba", str = "dog dog dog dog" should return false.
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
 * <p>
 * 要注意的是: key 和 val 是一一对应的,key不同,val 也不能一样;这是一种互为 k-v 的模式;
 * Input:
 * "abba"
 * "dog dog dog dog"
 * Output:
 * true
 * Expected:
 * false
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        String[] strs = str.split(" ");
        HashMap<Character, String> map = new HashMap<Character, String>();
        HashMap<String, Character> map2 = new HashMap<String, Character>();

        if (strs.length != pattern.length()) {
            return false;
        }

        for (int i = 0; i < pattern.length(); i++) {
            //只是只用put 方法就可以辨别出来;put 返回的是之前的值;
            String s = map.put(pattern.charAt(i), strs[i]);
            if (s != null && !s.equals(strs[i])) {
                return false;
            }
            Character c = map2.put(strs[i], pattern.charAt(i));
            if (c != null && c != pattern.charAt(i)) {
                return false;
            }
        }
        return true;
    }


    public boolean wordPattern2(String pattern, String str) {
        String[] strs = str.split(" ");
        if (strs.length != pattern.length()) {
            return false;
        }

        String[] ss = new String[26];
        for (int i = 0; i < pattern.length(); i++) {
            int index = pattern.charAt(i) - 97;
            if (ss[index] == null) {
                ss[index] = strs[i];
            } else {
                if (!ss[index].equals(strs[i])) {
                    return false;
                }
            }
        }
        HashSet<String> set = new HashSet<String>();
        for (int i = 0; i < ss.length; i++) {
            if (ss[i] != null) {
                if (!set.add(ss[i])) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {

    }
}
