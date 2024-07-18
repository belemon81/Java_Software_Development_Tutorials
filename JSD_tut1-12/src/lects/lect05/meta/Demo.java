package lects.lect05.meta;

public class Demo {
    public static <R, T> R someMethod(T value, String addition) {
        String s = value.toString() + addition;
        return (R) s;
    }

    public static void main(String[] args) {
        String out = Demo.<String, Integer>someMethod(15, "x");
        System.out.println(out);
    }


}
