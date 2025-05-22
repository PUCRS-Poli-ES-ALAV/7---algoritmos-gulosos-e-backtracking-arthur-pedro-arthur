import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class rainhas2 {
    
    static List<int[][]> solucoes = new ArrayList<>();
    static int contador = 0;
    static int contadorIteracoes = 0;
    static int contadorIsValid = 0;

    // Verifica se é seguro colocar uma rainha na posição (x,y)
    static boolean isValid(int board[][], int x, int y) {
        contadorIsValid++;

        // Verifica verticalmente para cima (mesma coluna)
        for (int i = x - 1; i >= 0; i--) {
            if (board[i][y] != 0) {
                return false;
            }
        }
        // Verifica horizontalmente para esquerda (mesma linha)
        for (int i = y - 1; i >= 0; i--) {
            if (board[x][i] != 0) {
                return false;
            }
        }
        // Verifica diagonal superior esquerda
        for (int i = 1; x - i >= 0 && y - i >= 0; i++) {
            if (board[x - i][y - i] != 0) {
                return false;
            }
        }
        // Verifica diagonal superior direita
        for (int i = 1; x - i >= 0 && y + i < board.length; i++) {
            if (board[x - i][y + i] != 0) {
                return false;
            }
        }
        return true;
    }

    // Função recursiva para posicionar N rainhas
    static void nrainhas(int n, int[][] board, int x, int y, int tam) {
        contadorIteracoes++;

        if (n == 0) {
            int[][] copia = new int[tam][tam];
            for (int i = 0; i < tam; i++) {
                copia[i] = Arrays.copyOf(board[i], board[i].length);
            }
            solucoes.add(copia);
            contador++;
            return;
        }

        if (x >= tam) {
            return;
        }

        if (isValid(board, x, y)) {
            int[][] new_board = new int[tam][tam];
            for (int i = 0; i < board.length; i++) {
                new_board[i] = Arrays.copyOf(board[i], board[i].length);
            }
            new_board[x][y] = 1;

            int newX = x;
            int newY = y + 1;
            if (newY == tam) {
                newX = x + 1;
                newY = 0;
            }

            nrainhas(n - 1, new_board, newX, newY, tam);
        }

        int newX = x;
        int newY = y + 1;
        if (newY == tam) {
            newX = x + 1;
            newY = 0;
        }

        if (newX < tam) {
            nrainhas(n, board, newX, newY, tam);
        }
    }

    public static void main(String[] args) {
        // int N = 9;
        // solucoes.clear();
        // contador = 0;

        // nrainhas(N, new int[N][N], 0, 0, N);

        // if (contador == 0) {
        //     System.out.println("Sem respostas válidas.");
        // } else {
        //     System.out.println("Total de soluções: " + contador);
        //     for (int[][] sol : solucoes) {
        //         for (int i = 0; i < N; i++) {
        //             for (int j = 0; j < N; j++) {
        //                 System.out.printf("%d ", sol[i][j]);
        //             }
        //             System.out.println();
        //         }
        //         System.out.println();
        //     }
        // }

        System.out.println("N\tSoluções\tIterações\tisValid Calls\tTempo(ms)");
        System.out.println("================================================================");
        
        for(int N = 1; N <= 11; N++) {
            solucoes.clear();
            contador = 0;
            contadorIteracoes = 0;
            contadorIsValid = 0;
            
            long startTime = System.nanoTime();
            nrainhas(N, new int[N][N], 0, 0, N);
            long endTime = System.nanoTime();
            
            double duration = (endTime - startTime)/1e6;
            
            System.out.printf("%d\t%9d\t%,12d\t%,14d\t%,.3f\n", 
                N, contador, contadorIteracoes, contadorIsValid, duration);
        }
    }
}