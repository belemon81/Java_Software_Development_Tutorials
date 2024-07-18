package lects.lect04;

public class MyThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new MyThread();
        t.setDaemon(true);
        t.start();
        // t.join(60000); // the calling thread (main)
        // waits for the referenced thread (t)
        Thread.sleep(2000);
        System.out.println("[MyThreadDemo] OK, I'm done waiting!");
    }
}
