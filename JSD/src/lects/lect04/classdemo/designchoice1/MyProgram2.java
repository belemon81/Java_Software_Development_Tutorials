package lects.lect04.classdemo.designchoice1;

import lects.lect04.classdemo.threadwait.MyThread;

import java.util.Scanner;

public class MyProgram2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter start or stop: ");
            String inp = sc.next();
            if (inp.equals("start")) {
                MyThread t = new MyThread();
                t.start();
            } else if (inp.equals("stop")) {
                break;
            }
        }
        System.out.println("Main thread goodbyes you!");
    }
}
