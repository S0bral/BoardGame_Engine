import java.util.Scanner;

public interface RegraPosicionamento {
    void executarJogada(Tabuleiro tabuleiro, Jogador jogador, Scanner scanner) throws InvalidMoveException;
}