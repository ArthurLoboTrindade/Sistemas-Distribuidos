package main;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Processo implements Runnable {
    String nome;
    int relogio;
    BlockingQueue<Mensagem> filaMensagens;
    Processo[] vizinhos;

    public Processo(String nome) {
        this.nome = nome;
        this.relogio = 0;
        this.filaMensagens = new LinkedBlockingQueue<>();
    }

    public void setVizinhos(Processo... vizinhos) {
        this.vizinhos = vizinhos;
    }

    public void eventoLocal() {
        this.relogio++;
        System.out.println(this.nome + " executou evento local. Relogio: " + this.relogio);
    }

    public void enviarMensagem(Processo destino, String conteudo) {
        this.relogio++;
        Mensagem msg = new Mensagem(this.nome, conteudo, this.relogio);
        System.out.println(this.nome + " enviando mensagem para " + destino.nome + ". Relogio: " + this.relogio);
        destino.filaMensagens.add(msg);
    }

    @Override
    public void run() {
        try {
            eventoLocal();
            
            if (this.vizinhos != null && this.vizinhos.length > 0) {
                enviarMensagem(this.vizinhos[0], "Requisicao de " + this.nome);
            }

            Mensagem msgRecebida = filaMensagens.take();
            this.relogio = Math.max(this.relogio, msgRecebida.timestamp) + 1;
            System.out.println(this.nome + " recebeu de " + msgRecebida.remetente + ". Relogio ajustado para: " + this.relogio);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}