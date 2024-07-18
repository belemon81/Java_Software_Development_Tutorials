package lects.lect05.generic;

public class SimpleGeneric<E> {
    void print(E x) {
        if (x != null) {
            System.out.println(x);
        } else {
            System.out.println("null");
        }
    }
}
