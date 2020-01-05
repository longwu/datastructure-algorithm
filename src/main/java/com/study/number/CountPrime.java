package com.study.number;

import java.util.ArrayList;
import java.util.List;

/**
 * 204. 计数质数
 * <p>
 * 统计所有小于非负整数 n 的质数的数量。
 * <p>
 * 示例:
 * <p>
 * 输入: 10
 * 输出: 4
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * <p>
 * https://leetcode-cn.com/problems/count-primes/
 */
public class CountPrime {
    public static void main(String[] args) {
        CountPrime c = new CountPrime();
        List<Integer> primeList = c.countPrimes(10);
        for (Integer p : primeList) {
            System.out.println(p);
        }
    }

    /**
     * 计算n以内所有质数的个数
     *
     * @param n
     * @return
     */
    public List<Integer> countPrimes(int n) {
        List<Integer> primeList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            if (isPrime(i))
                primeList.add(i);
        }
        return primeList;
    }

    public boolean isPrime(int num) {
        // 0和1不是质数
        if (num <= 1)
            return false;

        // 求开方根 会导致结果不准
//        int numSq = (int) Math.sqrt(num);
//        for (int i = 2; i <= numSq; i++) {
//            if (numSq % i == 0) {
//                return false;
//            }
//        }
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
