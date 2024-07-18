package lects.lect04;

public class MyRunnableDemo {
    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable());
        t.start();
        System.out.println("[MyRunnableDemo] OK, I'm done!");
    }
}
