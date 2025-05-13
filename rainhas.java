public class rainhas {
    static boolean isValid(int board[][], int x, int y) {
        for (int i = x-1; i <= 0; i--) {
            if (board[i][y] != 0) {
                return false;
            }
        }
        for (int i = y-1; i >= 0; i--) {
            if (board[x][i] != 0) {
                return false;
            }
        }

        for (int i = 1; i < Math.min(x, y); i++) {
            if (board[x-i][y-i] != 0) {
                return false;
            }
        }
        for (int i = 1; i < Math.min(board.length-x, y); i++) {
            if (board[x+i][y-i] != 0) {
                return false;
            }
        }

        return true;
    }

    static int[][] nrainhas(int n, int board[][]) {
        if (n == 0) {
            return board;
        }
        

        return board;
    }

    public static void main(String[] args) {
        
    }
}
