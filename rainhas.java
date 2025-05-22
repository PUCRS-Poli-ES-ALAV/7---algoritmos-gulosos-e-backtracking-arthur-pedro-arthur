import java.util.Arrays;

public class rainhas {
    static int contadorIteracoes = 0;
    static int contadorIsValid = 0;
    
    // Verifica se é seguro colocar uma rainha na posição (x,y)
    static boolean isValid(int board[][], int x, int y) {
        contadorIsValid++;  // Contagem de chamadas

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
    static int[][] nrainhas(int n, int board[][], int x, int y, int tam) {
        contadorIteracoes++;  // Contagem de iterações

        // Caso base: todas as rainhas foram posicionadas
        if (n == 0) {
            return board;
        }

        // Verifica se ultrapassou os limites do tabuleiro
        if (x >= tam) {
            return null;
        }

        // Se a posição atual é válida
        if (isValid(board, x, y)) {
            // Cria uma cópia do tabuleiro para não modificar o original
            int[][] new_board = new int[tam][tam];
            for (int i = 0; i < board.length; i++) {
                new_board[i] = Arrays.copyOf(board[i], board[i].length);
            }
            new_board[x][y] = 1;  // Coloca a rainha

            // Calcula próxima posição (varre linha por linha)
            int newX = x;
            int newY = y + 1;
            if (newY == tam) {  // Chegou ao final da linha
                newX = x + 1;
                newY = 0;
            }

            // Tenta posicionar a próxima rainha
            int[][] result = nrainhas(n - 1, new_board, newX, newY, tam);
            if (result != null) {  // Se encontrou solução
                return result;
            }
        }

        // Se não é válido, avança para próxima posição
        int newX = x;
        int newY = y + 1;
        if (newY == tam) {  // Chegou ao final da linha
            newX = x + 1;
            newY = 0;
        }

        // Verifica se ainda está dentro do tabuleiro
        if (newX >= tam) {
            return null;
        }

        // Continua a busca recursivamente
        return nrainhas(n, board, newX, newY, tam);
    }

    public static void main(String[] args) {
        // int N = 4;  // Número de rainhas/tamanho do tabuleiro
        // int[][] res = nrainhas(N, new int[N][N], 0, 0, N);
        
        // // Bloco que imprime o resultado
        // if (res != null) {
        //     for (int i = 0; i < N; i++) {
        //         for (int j = 0; j < N; j++) {
        //             System.out.printf("%d ", res[i][j]);
        //         }
        //         System.out.println();
        //     }
        // } else {
        //     System.out.println("Sem respostas válidas.");
        // }
        // // Fim do bloco

        // Bloco da tabela
        System.out.println("N\tSolução?\tIterações\tisValid Calls\tTempo(ms)");
        System.out.println("================================================================");
        
        for(int N = 1; N <= 12; N++) {
            contadorIteracoes = 0;
            contadorIsValid = 0;
            
            long startTime = System.nanoTime();
            int[][] res = nrainhas(N, new int[N][N], 0, 0, N);
            long endTime = System.nanoTime();
            
            double duration = (endTime - startTime)/1e6;
            String solucao = res != null ? "Sim" : "Não";
            
            System.out.printf("%d\t%-7s\t%,9d\t%,12d\t%,.3f\n", 
                N, solucao, contadorIteracoes, contadorIsValid, duration);
        }
        // Fim do bloco da tabela
    }
}