import java.util.InputMismatchException;
import java.util.Scanner;

public class PosicionamentoLivre implements RegraPosicionamento {

    @Override
    public void executarJogada(Tabuleiro tabuleiro, Jogador jogador, Scanner scanner) throws InvalidMoveException {
        try {
            System.out.println("Digite o número da linha (1-" + tabuleiro.getLinhas() + "):");
            int linha = scanner.nextInt() - 1;

            System.out.println("Digite o número da coluna (1-" + tabuleiro.getColunas() + "):");
            int coluna = scanner.nextInt() - 1;

            if (linha < 0 || linha >= tabuleiro.getLinhas() || coluna < 0 || coluna >= tabuleiro.getColunas()) {
                throw new InvalidMoveException("Posição fora dos limites do tabuleiro!");
            }

            if (!tabuleiro.fazerJogada(linha, coluna, jogador.getSimbolo())) {
                throw new InvalidMoveException("Essa posição já está ocupada!");
            }

        } catch (InputMismatchException e) {
            scanner.next();
            throw new InvalidMoveException("Entrada inválida! Digite apenas números.");
        }
    }
}