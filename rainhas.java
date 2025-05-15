import java.util.Arrays;

public class rainhas {
    static boolean isValid(int board[][], int x, int y) {
        for (int i = x-1; i >= 0; i--) {
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

    static int[][] nrainhas(int n, int board[][], int x, int y, int tam) {
        if (n == 0) {
            return board;
        }
        
        if(isValid(board, x, y)) {
            int[][] new_board = new int[tam][tam];
            for (int i = 0; i < board.length; i++) {
                new_board[i] = Arrays.copyOf(board[i], board[i].length);
            }
            new_board[x][y] = 1;
            if(y == tam - 1) {
                x = x + 1;
                y = 0;
            } else {
                y = y + 1;
            }
            
            return nrainhas(n-1, new_board, x, y, tam);
        }
        if(y == tam - 1) {
            x = x + 1;
            y = 0;
        } else {
            y = y + 1;
        }

        return nrainhas(n, board, x, y, tam);
    }

    public static void main(String[] args) {
        var res = nrainhas(4, new int[4][4], 0, 0, 4);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("%d ", res[i][j]);
            }
            System.out.println();
        }
    }
}
