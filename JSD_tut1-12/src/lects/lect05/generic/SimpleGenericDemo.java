package lects.lect05.generic;

public class SimpleGenericDemo {
    public static void main(String[] agrs) {
        SimpleGeneric<Integer> s = new SimpleGeneric();
        s.print(1);
        s.print(new Integer(1));
        // compile-time error
        //s.print("hello world");
        //s.print(1.0);
    }
}
