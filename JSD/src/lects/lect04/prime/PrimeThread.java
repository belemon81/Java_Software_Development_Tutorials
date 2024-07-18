package lects.lect04.prime;

public class PrimeThread extends Thread {
    private int from;
    private int to;

    public PrimeThread(int from, int to) {
        super();
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        for (int i = from; i <= to; i++) {
            if (isPrime(i)) {
                Main.saveResult(i);
            }
        }
        Main.addTask();
    }

    private boolean isPrime(int n) {
        if (n <= 1)
            return false;
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
        return true;
    }
}
