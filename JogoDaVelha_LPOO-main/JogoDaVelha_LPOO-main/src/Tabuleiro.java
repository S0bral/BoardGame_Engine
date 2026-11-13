public class Tabuleiro {

    private int linhas;
    private int colunas;
    private String[][] tabuleiro;

    public Tabuleiro(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.tabuleiro = new String[linhas][colunas];


        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                this.tabuleiro[i][j] = " ";
            }
        }
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }
    public String getSimbolo(int linha, int coluna) {
        return this.tabuleiro[linha][coluna];
    }

    public String getPosicao(int linha, int coluna) {
        if (linha >= 0 && linha < this.linhas && coluna >= 0 && coluna < this.colunas) {
            return this.tabuleiro[linha][coluna];
        }
        return "";
    }

    public void imprimirTabuleiro() {
        System.out.print("    ");
        for (int j = 0; j < this.colunas; j++) {
            System.out.print((j + 1) + "   ");
        }
        System.out.println();

        for (int i = 0; i < this.linhas; i++) {
            // Número da linha lateral
            System.out.print((i + 1) + "   ");

            for (int j = 0; j < this.colunas; j++) {
                System.out.print(this.tabuleiro[i][j]);
                if (j < this.colunas - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();

            if (i < this.linhas - 1) {
                System.out.print("   ");
                for (int j = 0; j < this.colunas; j++) {
                    System.out.print("---");
                    if (j < this.colunas - 1) {
                        System.out.print("+");
                    }
                }
                System.out.println();
            }
        }
    }

    public boolean fazerJogada(int linha, int coluna, String simbolo) {
            if (linha >= 0 && linha < this.linhas && coluna >= 0 && coluna < this.colunas && this.tabuleiro[linha][coluna].equals(" ")) {
                this.tabuleiro[linha][coluna] = simbolo;
                return true;
            }
            else{
                return false;
            }
    }

    public static void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public boolean verificarEmpate() {
        for (int i = 0; i < this.linhas; i++) {
            for (int j = 0; j < this.colunas; j++) {
                if (this.tabuleiro[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }


}
