package main;

class Mensagem {
    String remetente;
    String conteudo;
    int timestamp;

    public Mensagem(String remetente, String conteudo, int timestamp) {
        this.remetente = remetente;
        this.conteudo = conteudo;
        this.timestamp = timestamp;
    }
}