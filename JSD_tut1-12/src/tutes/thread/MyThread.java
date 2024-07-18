package tutes.thread;

public class MyThread extends Thread {
    private int start;
    private int end;
    private int maxNumOfDivisors;
    private int numFound;
    private boolean available = true;
    private boolean on = true;

    public MyThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    private static int getNumOfDivisors(int number) {
        int count = 0;
        int limit = (int) Math.ceil(Math.sqrt(number));
        for (int i = 2; i < limit; i++) {
            if (number % i == 0) count += 2;
        }
        return count;
    }

    public int getMaxNumOfDivisors() {
        return maxNumOfDivisors;
    }

    public int getNumFound() {
        return numFound;
    }

    public boolean isOn() {
        return on;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // done setting before running the next task,
    // or else it may start (or end) in wrong number
    synchronized public void reset(int start, int end) {
        this.start = start;
        this.end = end;
        this.on = true;
    }

    @Override
    public void run() {
        while (available) {
            // "on" may be false before the task done, as it is not synchronized
            synchronized (this) {
                if (on) {
                    double starttime = System.currentTimeMillis();
                    int maxNumOfDivisors = 0;
                    int number = 0;
                    for (int i = start; i <= end; i++) {
                        if (getNumOfDivisors(i) > maxNumOfDivisors) {
                            number = i;
                            maxNumOfDivisors = getNumOfDivisors(i);
                        }
                        if (System.currentTimeMillis() - starttime > 60000) {
                            System.out.println("STOP: time exceeds 1m!");
                            System.out.println("Stopping point: " + i);
                            break;
                        }
                    }
                    if (this.maxNumOfDivisors < maxNumOfDivisors) {
                        this.maxNumOfDivisors = maxNumOfDivisors;
                        this.numFound = number;
                    }
                    on = false;
                    System.out.println(Thread.currentThread().getName() + " (" + start + " - " + end + ") : " + number + " (" + maxNumOfDivisors + ")" + " in " + (System.currentTimeMillis() - starttime) + "ms");
                }
            }
        }
    }
}

