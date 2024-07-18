package tutes.tut12;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Ex3 {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 20, 24);

//        nums.stream()
//                .filter(x -> x % 2 == 1)
//                .map(x -> x * x)
//                .forEach(System.out::println);

        Predicate<Integer> isOdd = x -> x % 2 == 1;

//        nums.stream()
//                .filter(isOdd)
//                .map(num -> num * num)
//                .forEach(System.out::println);

        Function<List<Integer>, List<Integer>> filterOdd =
                list -> list.stream()
                        .filter(isOdd)

                        .collect(Collectors.toList());

        Consumer<List<Integer>> printOdd =
                list -> filterOdd.apply(list)
                        .forEach(System.out::println);

        printOdd.accept(nums);

        System.out.println();

        Predicate<Integer> isEven = isOdd.negate();
        Predicate<Integer> greaterThanTen = x -> x > 10;
        Predicate<Integer> lowerThanTwenty = x -> x < 20;
        Predicate<Character> isDigit = Character::isDigit;

        List<Integer> list2 = filterList(nums, isEven.or(greaterThanTen).and(lowerThanTwenty));
        list2.forEach(System.out::println);
    }

    public static List<Integer> filterList(List<Integer> list,
                                           Predicate<Integer> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }
}
