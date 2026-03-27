package main;

public class Sistemadistribuido {
    public static void main(String[] args) throws InterruptedException {
        Processo p1 = new Processo("P1");
        Processo p2 = new Processo("P2");
        Processo p3 = new Processo("P3");

        p1.setVizinhos(p2);
        p2.setVizinhos(p3);
        p3.setVizinhos(p1);

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        Thread t3 = new Thread(p3);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }
}