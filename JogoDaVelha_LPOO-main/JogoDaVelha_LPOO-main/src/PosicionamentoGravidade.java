import java.util.InputMismatchException;
import java.util.Scanner;

public class PosicionamentoGravidade implements RegraPosicionamento {

    @Override
    public void executarJogada(Tabuleiro tabuleiro, Jogador jogador, Scanner scanner) throws InvalidMoveException {
        try {
            System.out.println("Digite o número da coluna (1-" + tabuleiro.getColunas() + "):");
            int coluna = scanner.nextInt() - 1;

            if (coluna < 0 || coluna >= tabuleiro.getColunas()) {
                throw new InvalidMoveException("Coluna inválida! Escolha entre 1 e " + tabuleiro.getColunas() + ".");
            }

            for (int linha = tabuleiro.getLinhas() - 1; linha >= 0; linha--) {
                if (tabuleiro.fazerJogada(linha, coluna, jogador.getSimbolo())) {
                    return;
                }
            }

            throw new InvalidMoveException("A coluna " + (coluna + 1) + " já está cheia!");

        } catch (InputMismatchException e) {
            scanner.next();
            throw new InvalidMoveException("Entrada inválida! Digite apenas números.");
        }
    }
}