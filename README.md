# Engine Modular de Jogos de Tabuleiro (LPOO - Checkpoint 2)

Motor genérico de jogos de tabuleiro em grade desenvolvido em Java. A arquitetura desacopla o fluxo da partida das regras específicas de cada modalidade, permitindo executar diferentes jogos — como **Jogo da Velha**, **Connect Four** ou variações customizadas — exclusivamente por meio de arquivos de configuração **JSON**, sem alterar ou recompilar o código-fonte.

---

## Arquitetura e Conceitos de POO Aplicados

* **Inversão de Controle e Injeção de Dependência:** A classe `GameMaster` controla o ciclo de turnos, mas não conhece nenhum jogo específico. Ela recebe suas dependências (`Tabuleiro`, `RegraPosicionamento`, `CondicaoVitoria` e `List<Jogador>`) injetadas pelo `GameLoader`.
* **Polimorfismo (Padrão Strategy):**
  * **`RegraPosicionamento`:** Define como as peças entram no tabuleiro, implementada por `PosicionamentoLivre` (coordenadas de linha e coluna) e `PosicionamentoGravidade` (escolha de coluna com queda até a base).
  * **`CondicaoVitoria`:** Define o critério de término da partida, implementada por `CondicaoVitoriaAlinhamento`, que percorre uma matriz de direções (horizontal, vertical, diagonal principal e secundária) para detectar sequências contínuas de qualquer tamanho.
* **Configuração Dinâmica (`GameLoader`):** Faz o parsing e a validação estrutural dos arquivos `.json` utilizando a biblioteca **Gson**.
* **Tratamento de Exceções Personalizadas:**
  * **`InvalidConfigurationException`:** Intercepta arquivos ausentes, JSON malformado, dimensões inválidas, regras desconhecidas ou número insuficiente de jogadores antes de iniciar a partida.
  * **`InvalidMoveException`:** Trata entradas não numéricas no `Scanner`, coordenadas fora dos limites, posições já ocupadas e colunas cheias durante o jogo, mantendo a partida ativa sem encerrar o programa.

---

## Estrutura do Projeto

```text
├── games/
│   ├── jogo_da_velha.json
│   └── connect_four.json
├── src/
│   ├── Main.java
│   ├── GameLoader.java
│   ├── GameMaster.java
│   ├── Tabuleiro.java
│   ├── Jogador.java
│   ├── RegraPosicionamento.java
│   ├── PosicionamentoLivre.java
│   ├── PosicionamentoGravidade.java
│   ├── CondicaoVitoria.java
│   ├── CondicaoVitoriaAlinhamento.java
│   ├── InvalidConfigurationException.java
│   └── InvalidMoveException.java
└── README.md
```

---

## Como Executar

1. Certifique-se de ter o **Java JDK** instalado e a biblioteca **`com.google.code.gson:gson`** configurada nas dependências do projeto.
2. Execute a classe `Main.java`.
3. Escolha o jogo desejado no menu do terminal (`1` para Jogo da Velha ou `2` para Connect Four) e informe os nomes dos jogadores.

---

## Configuração de Jogos via JSON

Qualquer novo jogo baseado em grade e alinhamento pode ser criado adicionando um arquivo `.json` na pasta `games/` seguindo o esquema abaixo:

| Campo | Tipo | Valores Aceitos / Descrição |
| :--- | :--- | :--- |
| `nomeJogo` | `String` | Nome identificador do jogo. |
| `tabuleiro.linhas` | `int` | Número de linhas da grade (maior que `0`). |
| `tabuleiro.colunas` | `int` | Número de colunas da grade (maior que `0`). |
| `regraPosicionamento` | `String` | `"LIVRE"` (escolha de linha e coluna) ou `"GRAVIDADE"` (empilhamento por coluna). |
| `regraVitoria` | `String` | `"ALINHAMENTO"` (sequência contínua de peças). |
| `quantidadeVitoria` | `int` | Quantidade de peças seguidas necessárias para vencer. |
| `jogadores` | `Array` | Lista de objetos contendo o `"simbolo"` de cada jogador (mínimo de 2). |

### Exemplo de Configuração Customizada (`10x10`)

```json
{
  "nomeJogo": "Connect Four Gigante",
  "tabuleiro": {
    "linhas": 10,
    "colunas": 10
  },
  "regraPosicionamento": "GRAVIDADE",
  "regraVitoria": "ALINHAMENTO",
  "quantidadeVitoria": 4,
  "jogadores": [
    { "simbolo": "X" },
    { "simbolo": "O" }
  ]
}
```
