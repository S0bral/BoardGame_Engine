public class Jogador {

    private String nomeJogador;

    private String simbolo;

    public String getJogador() {
        return nomeJogador;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public Jogador (String nomeJogador, String simbolo){
        this.nomeJogador = nomeJogador;
        this.simbolo = simbolo;
    }
}



