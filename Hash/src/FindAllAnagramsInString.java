import java.util.ArrayList;
import java.util.List;

/**
 * Author: zhangxin
 * Time: 2016/11/6 0006.
 * Desc:
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * <p>
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * <p>
 * The order of output does not matter.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * s: "cbaebabacd" p: "abc"
 * <p>
 * Output:
 * [0, 6]
 * <p>
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 * <p>
 * Input:
 * s: "abab" p: "ab"
 * <p>
 * Output:
 * [0, 1, 2]
 * <p>
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class FindAllAnagramsInString {

    // 21 ms !!!
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> ans = new ArrayList<Integer>();

        if (p.length() > s.length()) {
            return ans;
        }

        int[] charCounts = new int[26];

        for (char c : p.toCharArray()) {
            charCounts[toInt(c)]++;
        }

        //*************至此已经初始化好一个 hash 数组****************
        int left = 0;
        int right = 0;
        int numDiff = p.length();
        //先在 s 的前 p.length() 的字符串中遍历一遍,遍历完后,right也就有了初始值,这样下面的循环 left 和 right 就可以保持一个 p.length() 的间隔
        for (right = 0; right < p.length(); right++) {
            char c = s.charAt(right);
            if (charCounts[toInt(c)] > 0) {
                // 表示 hash 中存在该 字符, numDiff--
                numDiff--;
            }
            //不管c是不是存在,都将其在数组中减去;后面的 left 还会在加进来;
            charCounts[toInt(c)]--;
        }

        //此时 numDiff 有两个结果,要么等于0,要么>0(绝对不会小于0)
        //如果=0,表示s的前 p.length()个字符串和 t 的字符串同构;
        //如果>0,说明s中存在p中不存在的字符,在该位的值已经是负值了;
        if (numDiff == 0) {
            ans.add(0);
        }


        //此时 right = p.length();left = 0;接下来的循环中,left 和 right 一直保持一个  p.length() 的间隔
        // 相当于  p.length()长度的窗口逐个字符的向后移动!!!!!!
        while (right < s.length()) {
            char leftChar = s.charAt(left++);
            // >=表明之前 hash 中存在该元素,先++,因为下面要将其在添加进来,如果不是之前 hash 中的元素,只是添加进来而不进行 ++;
            // 为什么要++,添加进来一个原有的进来,当然要++了
            // 你可能会疑问,==0也可能是一个之前不存在的字符,没有+也没有-啊,数组默认当前位就是0啊
            // 但是不要忘了,leftChar是之前上一个for循环中扫描过的,
            if (charCounts[toInt(leftChar)] >= 0) {
                numDiff++;
            }
            charCounts[toInt(leftChar)]++;

            char rightChar = s.charAt(right++);
            charCounts[toInt(rightChar)]--;
            if (charCounts[toInt(rightChar)] >= 0) {
                numDiff--;
            }

            // 一个美好的结局是 前者leftChar添加进来的字符正好是rightChar删除的字符,前者++,后者--,正好说明窗口向后移动一格,还能匹配;

            if (numDiff == 0) {
                ans.add(left);
            }

        }

        return ans;
    }

    private int toInt(char c) {
        return c - 'a';
    }

    // 450ms,一个一个回退,然后再前进一步,效率较慢...
    public List<Integer> findAnagrams2(String s, String p) {

        List<Integer> list = new ArrayList<Integer>();

        int sl = s.length();
        int pl = p.length();

        if (sl == 0 || pl == 0 || sl < pl) return list;

        int count = 0;
        int[] ar = new int[26];

        for (char c : p.toCharArray()) {
            ar[c - 'a']++;
        }

        for (int i = 0; i < sl; i++) {

            if (ar[s.charAt(i) - 'a'] > 0) {
                //s中的当前字符在p中
                ar[s.charAt(i) - 'a']--;
                count++;
            } else {
                //s中当前字符不在p中
                int k = i;
                //恢复之前的ar数组;
                while (count > 0) {
                    ar[s.charAt(k - count) - 'a']++;
                    count--; //逐步的 count==0了;
                    i--;
                }
            }

            if (count == pl) {
                list.add(i - pl + 1);
                count--;
                ar[s.charAt(i - pl + 1) - 'a']++;
            }
        }
        return list;
    }


    // 21 ms;
    public List<Integer> findAnagrams3(String s, String p) {
        List<Integer> list = new ArrayList<Integer>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;
        int[] hash = new int[256]; //character hash
        //record each character in p to hash
        for (char c : p.toCharArray()) {
            hash[c]++;
        }
        // 至此, p 的哈希存放在 hash 中;

        //two points, initialize count to p's length
        int left = 0, right = 0, count = p.length();
        while (right < s.length()) {
            //move right everytime, if the character exists in p's hash, decrease the count
            //current hash value >= 1 means the character is existing in p
            if (hash[s.charAt(right++)]-- >= 1) count--;

            //when the count is down to 0, means we found the right anagram
            //then add window's left to result list
            if (count == 0) list.add(left);

            //if we find the window's size equals to p, then we have to move left (narrow the window) to find the new match window
            //++ to reset the hash because we kicked out the left
            //only increase the count if the character is in p
            //the count >= 0 indicate it was original in the hash, cuz it won't go below 0
            if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0) count++;
        }
        return list;
    }


    public static void main(String[] args) {


        //System.out.println(new FindAllAnagramsInString().findAnagrams4("abab","ab"));
        //System.out.println(new FindAllAnagramsInString().findAnagrams2("cbaebabacd", "abc"));
        //System.out.println(new FindAllAnagramsInString().findAnagrams("abacbabc", "abc")); //1,2,3,5;
        System.out.println(new FindAllAnagramsInString().findAnagrams("ccadbaee", "ab")); //1,2,3,5;

    }


    public List<Integer> findAnagrams4(String s, String p) {
        List<Integer> list = new ArrayList<Integer>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;
        int[] hash = new int[26]; //character hash
        //record each character in p to hash
        for (char c : p.toCharArray()) {
            hash[c - 'a']++;
        }
        // 至此, p 的哈希存放在 hash 中;

        //two points, initialize count to p's length
        int left = 0, right = 0, count = p.length();
        while (right < s.length()) {
            //表明 right 对应的字符,存在于 hash 中;找到一个 count--;
            if (hash[s.charAt(right) - 'a'] > 0) {
                count--;
            }
            //---
            //不管找到没找到,hash中对应的值都--; 然后 right 右移;
            // ?? 为什么没找到也要--;
            hash[s.charAt(right) - 'a']--;
            right++;

            //表明找到了一个, list 添加
            if (count == 0) {
                list.add(left);
            }

            if (right - left == p.length()) {
                if (hash[s.charAt(left) - 'a'] >= 0) {
                    count++;
                }
                hash[s.charAt(left) - 'a']++;
                left++;
            }
        }
        return list;
    }
}
