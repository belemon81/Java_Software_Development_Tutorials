package lects.lect04.classdemo.designchoice2;

public class MyProgram {
    public static void main(String[] args) {
        Runnable t = new MyRunnable();
        Thread t1 = new Thread(t);
        t1.start();
    }
}
