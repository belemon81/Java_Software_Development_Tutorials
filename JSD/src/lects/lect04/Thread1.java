package lects.lect04;

public class Thread1 extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("I'm the best!");
            if (isInterrupted()) {
                break;
            }
        }
    }
}
