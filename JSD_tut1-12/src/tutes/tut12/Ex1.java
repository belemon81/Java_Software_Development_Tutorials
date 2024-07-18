package tutes.tut12;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ex1 {
    public static void main(String[] args) {
        ActionListener x = e -> {
            System.out.println("Clicked!");
        };
        Runnable r = () -> {
            System.out.println("In an anonymous class!");
        };
        Comparator<Integer> comp = (i1, i2) -> i1.compareTo(i2); // don't need to specify type (autodetected)
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Collections.sort(list, Integer::compareTo);
    }
}
