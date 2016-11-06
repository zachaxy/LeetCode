/**
 * Author: zhangxin
 * Time: 2016/11/5 0005.
 * Desc:
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * <p>
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * <p>
 * <p>
 * A partially filled sudoku which is valid.
 * <p>
 * Note:
 * A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {

        //判断行
        for (int i = 0; i < 9; i++) {
            int[] nums = new int[9];
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    if (nums[c - '1'] == 1) {
                        return false;
                    } else {
                        nums[c - '1'] = 1;
                    }
                }
            }
        }

        //判断列

        return true;
    }

    public boolean isValidSudoku2(char[][] board) {
        for (int i = 0; i < 9; i++) {
            int row = 0, column = 0, block = 0;
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = 1 << (board[i][j] - '0');
                    if ((num & row) != 0) return false;
                    row |= num;
                }

                if (board[j][i] != '.') {
                    int num = 1 << (board[j][i] - '0');
                    if ((num & column) != 0) return false;
                    column |= num;
                }

                int x = i / 3 * 3 + j / 3;
                int y = i % 3 * 3 + j % 3;
                if (board[x][y] != '.') {
                    int num = 1 << (board[x][y] - '0');
                    if ((num & block) != 0) return false;
                    block |= num;
                }
            }
        }
        return true;
    }


    public boolean isValidSudoku3(char[][] board) {
        for (int i = 0; i < 9; i++) {
            int row = 0, column = 0, block = 0;
            for (int j = 0; j < 9; j++) {
                //处理行
                char rowC = board[i][j];
                if (rowC != '.') {
                    int t = 1 << (rowC - '1');
                    if ((t & row) == 0) {  //表明当前数字和当前row并没有重复的数组
                        row |= t;
                    } else {
                        return false;
                    }
                }

                //处理列
                char columnC = board[j][i];
                if (columnC != '.') {
                    int t = 1 << (columnC - '1');
                    if ((t & column) == 0) {  //表明当前数字和当前row并没有重复的数组
                        column |= t;
                    } else {
                        return false;
                    }
                }

                //处理块
                int x = i / 3 * 3 + j / 3;
                int y = i % 3 * 3 + j % 3;
                char blockC = board[x][y];
                if (blockC != '.') {
                    int t = 1 << (blockC - '1');
                    if ((t & block) == 0) {
                        block |= t;
                    } else {
                        return false;
                    }
                }
            }

        }
        return true;
    }

    public static void main(String[] args) {
        int[][] a = {{00, 01}, {10, 11}};
        System.out.println(a[0][0]);
        System.out.println(a[0][1]);
        System.out.println(a[1][0]);
        System.out.println(a[1][1]);
    }
}
