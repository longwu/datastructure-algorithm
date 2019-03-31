package com.study.number;

/**
 * 啤酒2元一瓶，2个空瓶可以换一瓶，4个瓶盖可以换一瓶，现在给N元钱，总共能喝多少瓶啤酒？
 */
public class DrinkBeer {
    public static void main(String[] args) {
        int money = 10;

        drunk(money);

        int bottle = money / 2;
        int cap = money / 2;
        int drunk = money / 2;

        drunk2(bottle, cap, drunk);
    }

    /**
     * 使用迭代方法 计算出可以喝多少酒
     * <p>
     * 每次换完 需要将刚换得的酒瓶和盖子数 + 之前剩余的酒瓶数和盖子数
     *
     * @param money
     */
    private static void drunk(int money) {
        int drunk = money / 2;
        int bottle = money / 2;
        int cap = money / 2;

        //循环兑换, 只要满足剩余空瓶大于2 或者 瓶盖大于4 即可
        while (bottle >= 2 || cap >= 4) {
            //瓶子大于2,进行兑换
            if (bottle >= 2) {
                System.out.println("当前数目:空瓶数=" + bottle + ",瓶盖=" + cap + ",已喝=" + drunk + ",空瓶数够两个，现在换一瓶！");
                bottle -= 2; //减掉2个瓶子 用于换酒
                bottle++; //换来的酒 得到1个瓶子
                cap++;
                drunk++; //喝到的酒+1
            }
            if (cap >= 4) {
                System.out.println("当前数目:空瓶数=" + bottle + ",瓶盖=" + cap + ",已喝=" + drunk + ",瓶盖够四个，现在换一瓶！");
                cap -= 4; //减掉4个盖子 用于换酒
                cap++; //换来的酒 得到1个盖子
                bottle++; //换来的酒 得到1个瓶子
                drunk++; //喝到的酒+1
            }
        }

        System.out.println(String.format("喝了%d瓶酒,剩下%d瓶子,剩下%d盖子", drunk, bottle, cap));
    }


    /**
     * 使用递归方法求可以喝多少瓶酒
     *
     * @param bottle
     * @param cap
     * @param drunk
     */
    private static void drunk2(int bottle, int cap, int drunk) {
        if (bottle < 2 && cap < 4) return;

        int newDrunk = bottle / 2 + cap / 4;
        bottle = newDrunk + bottle % 2; //剩余瓶子数 = 换到的酒数 + 换酒后剩下的瓶子数
        cap = newDrunk + cap % 4; //剩余盖子数 = 换到的酒数 + 换酒后剩下的盖子数
        drunk += newDrunk; //更新已经喝到的酒数量
        System.out.println(String.format("喝了%d瓶酒,剩下瓶子%d个,剩下盖子%d个", drunk, bottle, cap));

        drunk2(bottle, cap, drunk);
    }
}
