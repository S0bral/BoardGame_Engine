import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GameLoader {

    public static GameMaster carregarJogo(String caminhoArquivo) throws InvalidConfigurationException {
        try (FileReader reader = new FileReader(caminhoArquivo)) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

            if (!json.has("tabuleiro") || !json.has("regraPosicionamento") ||
                    !json.has("regraVitoria") || !json.has("quantidadeVitoria") || !json.has("jogadores")) {
                throw new InvalidConfigurationException("O arquivo JSON está incompleto. Verifique os campos obrigatórios.");
            }

            JsonObject tabJson = json.getAsJsonObject("tabuleiro");
            int linhas = tabJson.get("linhas").getAsInt();
            int colunas = tabJson.get("colunas").getAsInt();

            if (linhas <= 0 || colunas <= 0) {
                throw new InvalidConfigurationException("Dimensões do tabuleiro inválidas (" + linhas + "x" + colunas + "). Devem ser maiores que zero.");
            }
            Tabuleiro tabuleiro = new Tabuleiro(linhas, colunas);

            String regraPos = json.get("regraPosicionamento").getAsString();
            RegraPosicionamento regraPosicionamento;
            switch (regraPos.toUpperCase()) {
                case "LIVRE":
                    regraPosicionamento = new PosicionamentoLivre();
                    break;
                case "GRAVIDADE":
                    regraPosicionamento = new PosicionamentoGravidade();
                    break;
                default:
                    throw new InvalidConfigurationException("Regra de posicionamento '" + regraPos + "' não reconhecida.");
            }

            String regraVit = json.get("regraVitoria").getAsString();
            int quantidadeVitoria = json.get("quantidadeVitoria").getAsInt();

            if (quantidadeVitoria <= 0 || (quantidadeVitoria > linhas && quantidadeVitoria > colunas)) {
                throw new InvalidConfigurationException("Quantidade para vitória (" + quantidadeVitoria + ") incompatível com o tabuleiro.");
            }

            CondicaoVitoria condicaoVitoria;
            switch (regraVit.toUpperCase()) {
                case "ALINHAMENTO":
                    condicaoVitoria = new CondicaoVitoriaAlinhamento(quantidadeVitoria);
                    break;
                default:
                    throw new InvalidConfigurationException("Regra de vitória '" + regraVit + "' não reconhecida.");
            }

            List<Jogador> jogadores = new ArrayList<>();
            JsonArray jogadoresJson = json.getAsJsonArray("jogadores");
            for (JsonElement item : jogadoresJson) {
                JsonObject jogadorObj = item.getAsJsonObject();
                String simbolo = jogadorObj.get("simbolo").getAsString();
                jogadores.add(new Jogador("", simbolo));
            }

            if (jogadores.size() < 2) {
                throw new InvalidConfigurationException("O jogo precisa ter pelo menos 2 jogadores configurados.");
            }

            return new GameMaster(tabuleiro, jogadores, condicaoVitoria, regraPosicionamento);

        } catch (FileNotFoundException e) {
            throw new InvalidConfigurationException("Arquivo de configuração não encontrado: " + caminhoArquivo);
        } catch (InvalidConfigurationException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidConfigurationException("Estrutura do JSON inválida: " + e.getMessage());
        }
    }
}