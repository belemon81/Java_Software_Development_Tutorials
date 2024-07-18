package lects.lect04;

public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread1 t1 = new Thread1();
        t1.start();
        Thread.sleep(5000);
        t1.interrupt();
    }
}
