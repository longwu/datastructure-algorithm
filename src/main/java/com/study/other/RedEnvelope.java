package com.study.other;

import java.util.Random;

/**
 * 红包随机分配算法
 */
public class RedEnvelope {
    public static void main(String[] args) {
        calculate(10.00, 0.01, 10);
    }

    private static void calculate(double totalMoney, double minMoney, int totalPeople) {
        for (int i = 1; i < totalPeople; i++) {
            //随机安全上限  剩下每人能获得的最大平均钱数 =  最大可能剩余的钱数/剩下的人数
            double leftMaxAvgMoney = (totalMoney - (totalPeople - i) * minMoney) / (totalPeople - i);
            //System.out.printf("剩下平均数为:%s\r\n", String.format("%.2f", leftMaxAvgMoney));
            //当前分配的红包数额
            //当前可分配红包数额的范围(leftMaxAvgMoney * 100 - minMoney * 100) + minMoney * 100)
            //最小为minMoney * 100, 最大为leftMaxAvgMoney * 100
            double usedMoney = (new Random().nextDouble() * ((leftMaxAvgMoney * 100 - minMoney * 100) + minMoney * 100)) / 100;

            totalMoney = totalMoney - usedMoney;

            System.out.printf("第%s个红包:%s元,剩下%s元\n", i, String.format("%.2f", usedMoney), String.format("%.2f", totalMoney));
        }

        System.out.printf("第%s个红包:%s元,剩下%s元\n", totalPeople, String.format("%.2f", totalMoney), 0);
    }
}
