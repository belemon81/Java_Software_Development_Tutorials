package tutes.thread;

public class Ex3 {
    private static int getNumOfDivisors(int number) {
        int count = 0;
        int limit = (int) Math.ceil(Math.sqrt(number));
        for (int i = 2; i < limit; i++) {
            if (number % i == 0) count += 2;
        }
        return count;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int maxNumOfDivisors = 0;
        int number = 0;
        for (int i = 1; i <= 200000; i++) {
            if (getNumOfDivisors(i) > maxNumOfDivisors) {
                number = i;
                maxNumOfDivisors = getNumOfDivisors(i);
            }
            if (System.currentTimeMillis() - startTime > 60000) {
                System.out.println("STOP: Time exceeds 1m!");
                System.out.println("Stopping point: " + i);
                break;
            }
        }
        System.out.println("Time consumed: " + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("Number with the largest num of divisors: " + number);
        System.out.println("Its num of divisors: " + maxNumOfDivisors);

    }

}
