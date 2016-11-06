/**
 * Author: zhangxin
 * Time: 2016/11/6 0006.
 * Desc:
 * Given two strings s and t, determine if they are isomorphic.
 * <p>
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * <p>
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
 * <p>
 * For example,
 * Given "egg", "add", return true.
 * <p>
 * Given "foo", "bar", return false.
 * <p>
 * Given "paper", "title", return true.
 * <p>
 * Note:
 * You may assume both s and t have the same length.
 */
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        // FIXME: 2016/11/6 0006 如果字符串中有 asc码==0的,会出错;
        //所以该用 int 类型存储;一了百了;

        int[] nums = new int[256];

        for (int i = 0; i < s.length(); i++) {

            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (nums[c1] != 0) {
                if (c2 != 0 && nums[c1] != c2) {
                    return false;
                } else if (c2 == 0 && nums[c1] != -1) {
                    return false;
                }
            } else {
                if (c2 != 0) {
                    nums[c1] = c2;
                } else {
                    nums[c1] = -1;
                }
            }


        }

        // NOTE:之前的测试中 ab,aa返回了 true,所以在进行了 s 匹配 t之后,还要继续进行一次 t 匹配

        nums = new int[256];

        for (int i = 0; i < s.length(); i++) {

            char c2 = s.charAt(i);
            char c1 = t.charAt(i);

            if (nums[c1] != 0) {
                if (c2 != 0 && nums[c1] != c2) {
                    return false;
                } else if (c2 == 0 && nums[c1] != -1) {
                    return false;
                }
            } else {
                if (c2 != 0) {
                    nums[c1] = c2;
                } else {
                    nums[c1] = -1;
                }
            }


        }
        return true;
    }

    //note:之前考虑问题疏忽了,可以使用双数组相互保存,这样效率更快;
    public boolean isIsomorphic2(String sString, String tString) {

        char[] s = sString.toCharArray();
        char[] t = tString.toCharArray();

        int length = s.length;
        if (length != t.length) return false;

        char[] sm = new char[256];
        char[] tm = new char[256];

        for (int i = 0; i < length; i++) {
            char sc = s[i];
            char tc = t[i];
            if (sm[sc] == 0 && tm[tc] == 0) {
                sm[sc] = tc;
                tm[tc] = sc;
            } else {
                if (sm[sc] != tc || tm[tc] != sc) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Short.MAX_VALUE);
        System.out.println(1111);
        System.out.println(Character.MAX_VALUE + 0);
        System.out.println(Character.MIN_VALUE + 0);
        System.out.println(1111);
        System.out.println(new IsomorphicStrings().isIsomorphic2("ab", "aa"));
    }
}
