package com.study.traceback;

/**
 * 4皇后问题
 * <p>
 * 皇后问题研究的是如何将 4个皇后放置在 4×4 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 每个皇后的攻击路线为皇后所在的同一行和列以及对角线(意味着这些位置不能放置其他皇后)
 * <p>
 * https://leetcode-cn.com/problems/n-queens/ n皇后问题
 * https://mp.weixin.qq.com/s/hTcRURIWs_9YOfwlOjsA3w 8皇后问题
 * <p>
 * 解决皇后问题，可以分为两个层面：
 * 1.找出第一种正确摆放方式，也就是深度优先遍历。
 * 2.找出全部的正确摆放方式，也就是广度优先遍历。
 */
public class Queen4 {

    // 棋盘长宽和皇后数量(这里棋盘长宽和皇后数量相等)
    private static int maxNum = 4;

    // 由于这里使用的是int数组，int的初始值是0，代表没有落子。当有皇后放置的时候，对应的元素值改为1。
    private static int chessBoard[][] = new int[maxNum][maxNum];

    public static void main(String[] args) {
        // 设置棋盘的长宽
        maxNum = 4;
        // 从第一行开始,一行一行递归放置皇后
        setQueen(0);
        // 打印整个棋盘 输出皇后的位置
        printChessBoard();
    }

    /**
     * 定义一个check方法,传入新皇后的落点,通过检查横纵向以及斜向是否存在皇后来判断该位置是否能放.
     *
     * @param x 新皇后的横坐标
     * @param y 新皇后的纵坐标
     * @return 该位置是否可放新皇后 false不可 true可以
     */
    private static boolean check(int x, int y) {
        for (int i = 0; i < y; i++) {
            // 检查同一列是否已经有皇后存在(横坐标x相同)
            if (chessBoard[x][i] == 1) {
                return false; // 同一行存在皇后
            }
            // 检查左斜上方是否有皇后, 左斜方坐标为 x-i, y-i
            // 由于是从上往下放皇后,所以只需要检测左斜上方是否有皇后即可, 所以x坐标范围是 0 到 x-1
            if (x - 1 - i >= 0 && chessBoard[x - 1 - i][y - 1 - i] == 1) {
                return false; //存在皇后
            }

            // 检查右斜上方是否有皇后, 右斜上方的坐标为 x +i, y-i
            if (x + 1 + i < maxNum && chessBoard[x + 1 + i][y - 1 - i] == 1) {
                return false;//存在皇后
            }
        }
        // 不存在皇后, 可以放新皇后
        return true;
    }

    /**
     * 通过递归+回溯进行放置新皇后
     * 在每一行放置新皇后, 一旦放到第5行就说明前面4行皇后都已放置成功,则结束放置
     *
     * @param y
     * @return
     */
    private static boolean setQueen(int y) {
        // 一旦放置到第5行的时候 说明放置所有皇后成功,结束递归
        if (y == maxNum) {
            System.out.println("所有皇后放置完成");
            return true;// //递归从外往内终止条件, 之后进行从内往外回溯, 回到上一行进行放皇后试验, y--
        }

        // 遍历y所在的当前行,逐一个列进行检查和放置皇后
        for (int x = 0; x < maxNum; x++) {
            // 清理当前行的皇后, 重新放置
            // 如果在当前行的某一列放置皇后失败,则需要将当前行之前放置的皇后清除,重新在下一列放置皇后
            for (int i = 0; i < maxNum; i++) {
                chessBoard[i][y] = 0;
            }
            // 检查该位置能否放置皇后
            System.out.printf("----检查第%d行,第%d列能否放置皇后------\r\n", y, x);

            if (check(x, y)) {
                // 如果可以, 放置皇后
                chessBoard[x][y] = 1;
                System.out.printf("----放置第%d行,第%d列成功------\r\n", y, x);
//                printChessBoard();
//                System.out.println("-------------");
                // 同时递归到下一行进行放置皇后,如果返回true,说明所有皇后已放好
                if (setQueen(y + 1)) {
                    // 递归达到终止条件,开始从内往外回溯, 这里会执行棋盘的行次数
//                    System.out.printf("----全部皇后放置完成------\r\n", x, y);
//                    printChessBoard();
//                    System.out.println("-------------");
                    return true; //递归从外往内终止条件, 之后进行从内往外回溯, 回到上一行进行放皇后试验, y--
                } else {
                    // 如果放置所有行失败, 则回溯到上一行,并且上一行之前放置的某列也失败,并从下一列继续尝试检查放置
                    //----放置第2行失败------
                    //----放置第1行,第2列失败------
                    System.out.printf("----放置第%d行,第%d列失败------\r\n", y, x);
//                    printChessBoard();
//                    System.out.println("-------------");
                }
            } else {
                System.out.printf("----检查发现第%d行,第%d列无法放入皇后------\r\n", y, x);
            }
        }
        // 如果当前行所有列都无法放置新皇后, 否则当前行放置失败
        System.out.printf("----放置第%d行失败------\r\n", y);
        // 这里的false会返回给递归中的setQueen()方法
        return false; //递归从外往内终止条件, 之后进行从内往外回溯, 回到上一行进行放皇后试验, y--
    }

    /**
     * 打印整个棋盘,输出皇后的位置
     */
    private static void printChessBoard() {
        for (int x = 0; x < maxNum; x++) {
            for (int y = 0; y < maxNum; y++) {
                System.out.print(chessBoard[x][y] + " ");
            }
            System.out.println();
        }
    }
}
