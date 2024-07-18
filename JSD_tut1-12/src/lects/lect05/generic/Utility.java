package lects.lect05.generic;

public class Utility {
    public static <T> T getMidpoint(T[] a) {
        return a[a.length / 2];
    }

    public static <T> T getFirst(T[] a) {
        return a[0];
    }

    public static void main(String[] args) {
        String[] b = {"A", "B", "C"};
        Double[] c = {1.0, 1.5, 2.0};
        String midString = Utility.getMidpoint(b);
        System.out.println(midString);
        Double firstNumber = Utility.getFirst(c);
        System.out.println(firstNumber);
    }
}
