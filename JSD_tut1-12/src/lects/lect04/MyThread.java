package lects.lect04;

public class MyThread extends Thread {
    @Override
    public void run() {
        String[] lines = "Hello from my thread".split(" ");
        for (String line : lines) {
            System.out.println("[MyThread] " + line);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Oops, I'm interrupted. Bye!");
                return;
            }
        }
    }
}
