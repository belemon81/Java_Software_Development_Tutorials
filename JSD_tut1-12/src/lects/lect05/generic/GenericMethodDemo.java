package lects.lect05.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenericMethodDemo {
    // naive code
    public static void fromArrayToCollection(Object[] a, Collection<?> c) {
        for (Object o : a) {
            // Compile time error
            // c.add(o);
        }
    }

    // generic method
    static <T> void fromArrayToCollectionGM(T[] a, Collection<T> c) {
        for (T o : a) {
            c.add(o); // Correct
        }
    }

    public static void main(String[] agrs) {
        // array
        Integer[] n = {1, 2, 3, 4, 5};
        List<Number> c = new ArrayList<Number>();

        fromArrayToCollectionGM(n, c);

        // other tests
        Object[] oa = new Object[100];
        Collection<Object> co = new ArrayList<Object>();
        fromArrayToCollection(oa, co); // T inferred to be Object

        String[] sa = new String[100];
        Collection<String> cs = new ArrayList<String>();
        fromArrayToCollection(sa, cs); // T inferred to be String
        fromArrayToCollection(sa, co); // T inferred to be Object

        Integer[] ia = new Integer[100];
        Float[] fa = new Float[100];
        Number[] na = new Number[100];
        Collection<Number> cn = new ArrayList<Number>();
        fromArrayToCollection(ia, cn); // T inferred to be Number
        fromArrayToCollection(fa, cn); // T inferred to be Number
        fromArrayToCollection(na, cn); // T inferred to be Number
        fromArrayToCollection(na, co); // T inferred to be Object

        // compile-time error (?)
        fromArrayToCollection(na, cs);

        //print
        print(c);
    }

    private static void print(Collection<?> c) {
        for (Object m : c) {
            System.out.println(m);
        }
    }
}
