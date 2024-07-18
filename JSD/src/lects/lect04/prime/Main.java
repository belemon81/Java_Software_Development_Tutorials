package lects.lect04.prime;

import java.util.LinkedList;
import java.util.TreeSet;

public class Main {
    private static int nextStart = 0;
    private static TreeSet<Integer> foundPrimes = new TreeSet<>();
    // task poll
    private static LinkedList<PrimeThread> taskQueue = new LinkedList<>();

    public synchronized static void addTask() {
        if (nextStart < 1000000) {
            // System.out.println("Going to search between "
            //         + nextStart + " and " + (nextStart + 999));
            taskQueue.add(new PrimeThread(nextStart, nextStart + 999));
            nextStart = nextStart + 1000;
            taskQueue.remove().start(); // start the next one in line
        }
    }

    public synchronized static void saveResult(int prime) {
        foundPrimes.add(prime);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            addTask(); // 10 times
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("Found primes: " + foundPrimes.size());
            Thread.sleep(1000);
        }
    }
}
