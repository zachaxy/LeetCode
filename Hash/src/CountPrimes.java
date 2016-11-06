/**
 * Author: zhangxin
 * Time: 2016/11/6 0006.
 * Desc:
 * Count the number of prime numbers less than a non-negative number, n.
 * 计算小于n(n非负)的质数的个数;
 * <p>
 * NOTE:
 * hint:
 * (1)Let's start with a isPrime function. To determine if a number is prime, we need to check if it is not divisible by any number less than n.
 * The runtime complexity of isPrime function would be O(n) and hence counting the total prime numbers up to n would be O(n2).
 * Could we do better?
 * <p>
 * (2)As we know the number must not be divisible by any number > n / 2, we can immediately cut the total iterations half by dividing only up to n / 2. Could we still do better?
 * <p>
 * (3)Let's write down all of 12's factors:
 * 2 × 6 = 12
 * 3 × 4 = 12
 * 4 × 3 = 12
 * 6 × 2 = 12
 * As you can see, calculations of 4 × 3 and 6 × 2 are not necessary. Therefore, we only need to consider factors up to √n because,
 * if n is divisible by some number p, then n = p × q and since p ≤ q, we could derive that p ≤ √n.
 * Our total runtime has now improved to O(n1.5), which is slightly better. Is there a faster approach?
 * <p>
 * (4)The Sieve of Eratosthenes is one of the most efficient ways to find all prime numbers up to n.
 * But don't let that name scare you, I promise that the concept is surprisingly simple.
 * We start off with a table of n numbers. Let's look at the first number, 2. We know all multiples of 2 must not be primes, so we mark them off as non-primes.
 * Then we look at the next number, 3. Similarly, all multiples of 3 such as 3 × 2 = 6, 3 × 3 = 9, ... must not be primes, so we mark them off as well.
 * Now we look at the next number, 4, which was already marked off. What does this tell you? Should you mark off all multiples of 4 as well?
 * <p>
 * (5)4 is not a prime because it is divisible by 2, which means all multiples of 4 must also be divisible by 2 and were already marked off.
 * So we can skip 4 immediately and go to the next number, 5.
 * Now, all multiples of 5 such as 5 × 2 = 10, 5 × 3 = 15, 5 × 4 = 20, 5 × 5 = 25, ... can be marked off.
 * There is a slight optimization here, we do not need to start from 5 × 2 = 10. Where should we start marking off?
 * <p>
 * (6)In fact, we can mark off multiples of 5 starting at 5 × 5 = 25,
 * because 5 × 2 = 10 was already marked off by multiple of 2, similarly 5 × 3 = 15 was already marked off by multiple of 3.
 * Therefore, if the current number is p, we can always mark off multiples of p starting at p2,
 * then in increments of p: p2 + p, p2 + 2p, ... Now what should be the terminating loop condition?
 * <p>
 * (7)It is easy to say that the terminating loop condition is p < n, which is certainly correct but not efficient.
 * Do you still remember Hint #3?
 * <p>
 * (8)Yes, the terminating loop condition can be p < √n, as all non-primes ≥ √n must have already been marked off.
 * When the loop terminates, all the numbers in the table that are non-marked are prime.
 * <p>
 * (9)The Sieve of Eratosthenes uses an extra O(n) memory and its runtime complexity is O(n log log n).
 * For the more mathematically inclined readers, you can read more about its algorithm complexity on Wikipedia.
 */
public class CountPrimes {

    public int countPrimes(int n) {
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (isPrime(i)) count++;
        }
        return count;
    }

    //最简单的思路,逐个循环;判断是不是质数;
    private boolean isPrime(int num) {
        if (num <= 1) return false;
        // Loop's ending condition is i * i <= num instead of i <= sqrt(num)
        // to avoid repeatedly calling an expensive function sqrt().
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }


    //note: 埃拉托斯特尼筛法 寻找质数;
    public int countPrimes2(int n) {
        boolean[] isPrime = new boolean[n];
        //首先进行一次初始化,全部初始化为 true,其实默认初始化是false,也可以这样用啊;
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }
        // Loop's ending condition is i * i < n instead of i < sqrt(n)
        // to avoid repeatedly calling an expensive function sqrt().
        //截止是 i*i<n,因为如果大于n的开平方后,会导致重复的数据;
        //内部还有 j = i*i开始的循环,可以保证整n内所有数据都被遍历到
        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) continue;
            // note:j 从 i*i 开始,请看(6)解释;
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) count++;
        }
        return count;
    }

    //针对埃拉托斯特尼筛法,更加简洁的写法;
    public int countPrimes3(int n) {
        boolean[] m = new boolean[n];
        int count = 0;
        //不进行初始化,默认就是false,表示是素数,true 表示是合数;
        for (int i = 2; i * i < n; i++) {
            if (m[i]) {
                continue;
            }
            // FIXME: 2016/11/6 0006 当插入的数据为499979时,存在 i = 46349,那么 j = -2146737495,其实是溢出了!!!;
            for (int j = i * i; j < n; j = j + i) {
                m[j] = true; //这里设置的都是合数;
            }
        }
        for (int i = 2; i < n; i++) {
            if (!m[i]) count++;
        }
        return count;
    }


    //更加简洁的写法;
    public int countPrimes4(int n) {
        boolean[] m = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (m[i]) {
                continue;
            }
            count++;
            // FIXME: 2016/11/6 0006 当插入的数据为499979时,存在 i = 46349,那么 j = -2146737495,其实是溢出了!!!;
            for (int j = i; j < n; j = j + i) {
                m[j] = true;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        System.out.println(new CountPrimes().countPrimes2(599979));
        long l2 = System.currentTimeMillis();
        System.out.println(new CountPrimes().countPrimes3(599979));
        long l3 = System.currentTimeMillis();
        System.out.println(new CountPrimes().countPrimes4(599979));
        long l4 = System.currentTimeMillis();

        System.out.println(l2 - l1);
        System.out.println(l3 - l2);
        System.out.println(l4 - l3);
    }
}
