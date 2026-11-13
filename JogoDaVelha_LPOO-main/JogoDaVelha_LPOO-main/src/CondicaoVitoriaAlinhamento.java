public class CondicaoVitoriaAlinhamento implements CondicaoVitoria {

    private int quantidade;

    public CondicaoVitoriaAlinhamento(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean verificarVitoria(Tabuleiro tabuleiro, Jogador jogador) {
        String simbolo = jogador.getSimbolo();
        int linhas = tabuleiro.getLinhas();
        int colunas = tabuleiro.getColunas();

        int[][] direcoes = {
                {0, 1},  // Horizontal (para a direita)
                {1, 0},  // Vertical (para baixo)
                {1, 1},  // Diagonal principal (baixo-direita)
                {1, -1}  // Diagonal secundária (baixo-esquerda)
        };

        for (int l = 0; l < linhas; l++) {
            for (int c = 0; c < colunas; c++) {
                for (int[] dir : direcoes) {
                    if (contarSequencia(tabuleiro, l, c, dir[0], dir[1], simbolo) >= this.quantidade) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int contarSequencia(Tabuleiro tabuleiro, int lInicial, int cInicial, int dLinha, int dColuna, String simbolo) {
        int contagem = 0;
        int l = lInicial;
        int c = cInicial;

        while (l >= 0 && l < tabuleiro.getLinhas() && c >= 0 && c < tabuleiro.getColunas()) {
            if (tabuleiro.getSimbolo(l, c).equals(simbolo)) {
                contagem++;
                if (contagem == this.quantidade) {
                    return contagem;
                }
            } else {
                break;
            }
            l += dLinha;
            c += dColuna;
        }
        return contagem;
    }
}