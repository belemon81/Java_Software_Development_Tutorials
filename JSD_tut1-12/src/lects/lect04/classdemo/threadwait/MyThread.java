package lects.lect04.classdemo.threadwait;

public class MyThread extends Thread {
    public static String result = "";

    @Override
    public void run() {
        int n = 10 + (int) (Math.random() * 30);
        for (int i = 1; i < n; i++) {
            System.out.println(
                    this.getName() + ": " + i + "s");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        result += this.getName() + " counted to " + n + "\n";
    }
}
