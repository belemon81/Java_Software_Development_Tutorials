package tutes.thread;

public class MyApp {
    private static final int THREAD_COUNT = 10;
    private static final int RANGE = 2000;

    public static void main(String[] args) {
        MyThread[] workers = new MyThread[THREAD_COUNT];
        int tmp = 1, max = 0, num = 0;
        double startTime = System.currentTimeMillis();

        for (int i = 0; i < workers.length; i++) {
            workers[i] = new MyThread(tmp, tmp + RANGE - 1);
            tmp += RANGE;
            workers[i].start();
        }

        while (tmp < 200000) {
            for (int i = 0; i < workers.length; i++) {
                if (tmp >= 200000) {
                    break;
                }
                if (!workers[i].isOn()) {
                    workers[i].reset(tmp, tmp + RANGE - 1);
                    tmp += RANGE;
                }
                if (max < workers[i].getMaxNumOfDivisors()) {
                    max = workers[i].getMaxNumOfDivisors();
                    num = workers[i].getNumFound();
                }
            }
        }

        for (MyThread worker : workers) {
            worker.setAvailable(false);
            try {
                worker.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (max < worker.getMaxNumOfDivisors()) {
                max = worker.getMaxNumOfDivisors();
                num = worker.getNumFound();
            }
        }

//        workers[0] = new MyThread(1,20000);
//        workers[1] = new MyThread(20001,40000);
//        workers[2] = new MyThread(40001,60000);
//        workers[3] = new MyThread(60001,80000);
//        workers[4] = new MyThread(80001,100000);
//        workers[5] = new MyThread(100001,120000);
//        workers[6] = new MyThread(120001,140000);
//        workers[7] = new MyThread(140001,160000);
//        workers[8] = new MyThread(160001,180000);
//        workers[9] = new MyThread(180001,200000);

//        int[] works = {40000, 32000, 25000, 20000, 18000, 16000, 14000, 13000, 12000, 10000};
//        int tmp = 1;
//        for (int i =0; i < workers.length; i++) {
//            workers[i] = new MyThread(tmp, tmp+ works[i] - 1);
//            tmp += works[i];
//        }

//        for (Thread worker : workers) {
//            worker.start();
//
        System.out.println("All the threads finished!");
        System.out.println("Result: " + num + " (" + max + "), found in " + (System.currentTimeMillis() - startTime) + "ms");
    }
}
