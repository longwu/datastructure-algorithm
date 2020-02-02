package com.study.traceback;

public class Queen4_2 {

    // 棋盘的行和列, 以及皇后数量
    private static int maxNum = 4;

    private static int[][] chessBoard = new int[maxNum][maxNum];

    public static void main(String[] args) {
        maxNum = 4;

        // 从第0行开始放置皇后
        setQueen(0);

        printChessBoard();
    }

    /**
     * 按行进行放置皇后
     *
     * @param y 行号
     * @return
     */
    private static boolean setQueen(int y) {
        if (y == maxNum)
            return true; // 结束递归条件

        // 遍历第y行的每一列, 检查并放置皇后, 如果下一行放置失败同时进行回溯,从下一列重新放置
        for (int x = 0; x < maxNum; x++) {
            // 清理当前行的数据, 重新放置
            for (int i = 0; i < maxNum; i++) {
                chessBoard[i][y] = 0;
            }

            // 检查当前位置能否放皇后
            if (check(x, y)) {
                // 放入皇后
                chessBoard[x][y] = 1;

                // 递归进入下一行继续操作, 这里不能返回false
                if (setQueen(y + 1)) {
                    return true; //所有皇后放置成功,递归达到终止条件 y--
                }
            }
        }
        // 当前行都无法放置皇后, 递归达到终止条件 y--
        return false;
    }


    /**
     * 检查落子的纵上方和左右斜上方是否有皇后
     *
     * @param x 落子的横坐标
     * @param y 落子的纵坐标
     * @return
     */
    private static boolean check(int x, int y) {
        // 从当前行往上遍历
        for (int i = 0; i < y; i++) {
            // 检查纵上方
            if (chessBoard[x][y - 1 - i] == 1)
                return false;
            // 检查左斜上方
            if (x - 1 - i >= 0 && chessBoard[x - 1 - i][y - 1 - i] == 1) {
                return false;
            }
            // 检查右斜上方
            if (x + 1 + i < maxNum && chessBoard[x + 1 + i][y - 1 - i] == 1) {
                return false;
            }
        }
        return true;
    }

    private static void printChessBoard() {
        for (int x = 0; x < maxNum; x++) {
            for (int y = 0; y < maxNum; y++) {
                System.out.print(chessBoard[x][y] + " ");
            }
            System.out.println();
        }
    }
}
