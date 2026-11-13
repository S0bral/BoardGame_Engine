import java.util.List;
import java.util.Scanner;

public class GameMaster {

    private Tabuleiro tabuleiro1;
    private List<Jogador> jogadores;
    private CondicaoVitoria condicaoVitoria;
    private RegraPosicionamento regraPosicionamento;

    public GameMaster(Tabuleiro tabuleiro1, List<Jogador> jogadores, CondicaoVitoria condicaoVitoria, RegraPosicionamento regraPosicionamento) {
        this.tabuleiro1 = tabuleiro1;
        this.jogadores = jogadores;
        this.condicaoVitoria = condicaoVitoria;
        this.regraPosicionamento = regraPosicionamento;
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < this.jogadores.size(); i++) {
            Jogador jogador = this.jogadores.get(i);
            System.out.println("Digite o nome do jogador " + (i + 1) + " (" + jogador.getSimbolo() + "): ");
            String nome = scanner.next();
            jogador.setJogador(nome);
        }

        int turnoAtual = 0;
        Jogador jogadorAtual = this.jogadores.get(turnoAtual);
        String mensagemErro = null;

        while (true) {
            Tabuleiro.limparTela();
            tabuleiro1.imprimirTabuleiro();

            if (mensagemErro != null) {
                System.out.println("Jogada inválida: " + mensagemErro);
                mensagemErro = null;
            }

            System.out.println("O jogador atual é: " + jogadorAtual.getJogador() + " (" + jogadorAtual.getSimbolo() + ")");

            try {
                this.regraPosicionamento.executarJogada(this.tabuleiro1, jogadorAtual, scanner);

                if (this.condicaoVitoria.verificarVitoria(this.tabuleiro1, jogadorAtual)) {
                    Tabuleiro.limparTela();
                    tabuleiro1.imprimirTabuleiro();
                    System.out.println("Parabéns, o jogador " + jogadorAtual.getJogador() + " venceu!");
                    break;
                }

                if (tabuleiro1.verificarEmpate()) {
                    Tabuleiro.limparTela();
                    tabuleiro1.imprimirTabuleiro();
                    System.out.println("Empate!");
                    break;
                }

                turnoAtual = (turnoAtual + 1) % this.jogadores.size();
                jogadorAtual = this.jogadores.get(turnoAtual);

            } catch (InvalidMoveException e) {
                mensagemErro = e.getMessage();
            }
        }
    }
}