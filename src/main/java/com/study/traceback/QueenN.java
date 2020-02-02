package com.study.traceback;

/**
 * N皇后问题, 找出一种可以放置n个皇后的方法,使得每个皇后之间无法互相攻击
 *
 * 使用深度优先搜索dfs找出一种可以放置皇后的方法
 *
 * 具体做法为递归+回溯
 */
public class QueenN {

    private static int N = 0;
    private static int[][] chessBoard;

    public static void main(String[] args) {
        //createChessBoard(4);
        //createChessBoard(5);
        createChessBoard(8);
        setQueen(0);
        printChessBoard();
    }

    private static void createChessBoard(int n){
        N = n;
        chessBoard = new int[N][N];
    }

    private static boolean setQueen(int y) {
        if (y == N)
            return true;

        for (int x = 0; x < N; x++) {
            // 清理当前行的数据
            for (int i = 0; i < N; i++) {
                chessBoard[i][y] = 0;
            }

            if (check(x, y)) {
                chessBoard[x][y] = 1;

                if (setQueen(y + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean check(int x, int y) {
        // 从当前位置的上一行开始往上遍历
        for (int i = 0; i < y; i++) {
            // 检查纵向
            if (chessBoard[x][y - 1 - i] == 1)
                return false;

            // 检查左斜
            if (x - 1 - i >= 0 && chessBoard[x - 1 - i][y - 1 - i] == 1)
                return false;

            // 检查右斜
            if (x + 1 + i < N && chessBoard[x + 1 + i][y - 1 - i] == 1)
                return false;
        }
        return true;
    }

    private static void printChessBoard(){
        for(int x = 0; x < N; x ++){
            for(int y =0; y < N; y++){
                System.out.print(chessBoard[x][y] + " ");
            }
            System.out.println();
        }
    }
}
