package lects.lect04.classdemo.threadwait;

public class MyProgram {
    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new MyThread();
        t0.start();
        Thread t1 = new MyThread();
        t1.start();
        t0.join();
        t1.join();
        // print something when both threads have finished
        System.out.println("They both finished!");
        System.out.println(MyThread.result);
    }
}
