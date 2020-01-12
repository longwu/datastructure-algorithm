package com.study.number;

/**
 * 买卖股票的最佳时机 II
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 */
public class BestTimeToBuyAndSellStockII {

    /**
     * 同一支股票卖完还能再买, 也能买了再卖.
     * 但是不能连续买两次(不卖)
     *
     * 可以通过贪心法,波峰法以及暴力法来解答
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        //System.out.println(maxProfit(prices));
        System.out.println(maxProfit2(prices));

        int[] prices2 = {1, 2, 3, 4, 5};
        //System.out.println(maxProfit(prices2));
        System.out.println(maxProfit2(prices2));

        int[] prices3 = {7, 6, 4, 3, 1};
        //System.out.println(maxProfit(prices3));
        System.out.println(maxProfit2(prices3));
    }


    /**
     * 使用贪心算法, 每支股票的利润都不放过
     *
     * 循环遍历,支要下一个股票比当前股票贵,我就买当前的,然后卖下一个,获取下一个和当前之间的利润
     *
     * 时间复杂度O(n) 空间复杂度O(1)
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            // 前一个数小于后一个就买, 因为先买再卖能赚
            if (prices.length > i + 1 && prices[i] < prices[i + 1]) {
                profit += prices[i + 1] - prices[i];
            }
        }
        return profit;
    }

    /**
     * 峰谷法
     *
     * 循环数组, 先找波谷(用于先买),再找波峰(用于后卖), 利润=波峰-波谷
     *
     * 波谷为当前价格小于下一支股票, 波峰为当前价格大于下一支股票
     *
     * 时间复杂度：O(n)。遍历一次。
     *
     * 空间复杂度：O(1)。需要常量的空间。
     *
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        int i = 0;
        int valley;
        int peak;
        int maxprofit = 0;
        // 遍历每个股票
        while (i < prices.length - 1) {
            // 找波谷, 波谷为当前股票价格低于下一支股票
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];// 记录波谷
            // 找波峰, 波峰为当前股票价格高于下一支股票
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peak = prices[i]; // 记录波峰

            // 利润 = 波峰 - 波谷
            maxprofit += peak - valley;
        }
        return maxprofit;
    }
}
