package lects.lect04;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        String[] lines = "Hello from my runnable".split(" ");
        for (String line : lines) {
            System.out.println("[MyRunnable] " + line);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Oops, I'm interrupted. Bye!");
                return;
            }
        }
    }
}
