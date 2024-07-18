package tutes.tut12;

import java.util.Arrays;
import java.util.List;

public class Ex2 {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(3, 5, 1, 2, 4);
        nums.parallelStream() // run on multiple CPU core, does not guarantee the elements' order
                // .stream()
                .filter(val -> val > 1) // intermediate
                // .forEach(val -> System.out.println(val));
                .forEach(System.out::println); // terminal
        System.out.println();

        // Consumer<Integer> csm = (integer) -> {
        //     System.out.println(integer);
        // };

        // Consumer<Integer> csm = System.out::println;

        nums.parallelStream()
                // .stream()
                .filter(Ex2::isEven) // val -> val % 2 == 1
                .reduce(Integer::sum) // (val1, val2) -> val1 + val2
                // .get()
                .ifPresent(System.out::println); // method references
        System.out.println();

        nums.parallelStream()
                // .stream()
                .filter(Ex2::isEven)
                .map(val -> Math.pow(val, 2))
                .forEach(System.out::println);
    }

    public static boolean isEven(int x) {
        return x % 2 == 0;
    }
}
