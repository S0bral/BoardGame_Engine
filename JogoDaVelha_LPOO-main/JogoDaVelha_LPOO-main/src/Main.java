import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Selecione o jogo:");
            System.out.println("1 - Jogo da Velha");
            System.out.println("2 - Connect Four");
            int opcao = scanner.nextInt();

            String caminhoConfig;
            if (opcao == 1) {
                caminhoConfig = "games/jogo_da_velha.json";
            } else if (opcao == 2) {
                caminhoConfig = "games/connect_four.json";
            } else {
                System.out.println("Opção inválida.");
                return;
            }

            GameMaster jogo = GameLoader.carregarJogo(caminhoConfig);
            jogo.iniciar();

        } catch (InvalidConfigurationException e) {
            System.out.println("Erro ao carregar o jogo:");
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Digite apenas números.");
        }
    }
}