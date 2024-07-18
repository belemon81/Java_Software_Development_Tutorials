package lects.lect05.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class GenericSubtypingDemo {
    private static void error() {
        List<String> ls = new ArrayList<String>(); // 1
        // compile-time error
        // List<Object> lo = ls; // 2
    }

    public static void printCollection(Collection c) {
        Iterator i = c.iterator();
        for (int k = 0; k < c.size(); k++) {
            System.out.println(i.next());
        }
    }

    public static void printCollectionRestricted(Collection<Object> c) {
        for (Object e : c) {
            System.out.println(e);
        }
    }

    public static void printCollectionGeneric(Collection<?> c) {
        for (Object e : c) {
            System.out.println(e);
        }
    }

    public static void addCollectionError(Collection<?> c) {
        // compile-time error
        //c.add(new Integer(1));
    }

    public static void addCollectionRestricted(Collection<?> c) {
        // still compile-time error
    /*
    if (c instanceof List<?>) {
      List<?> l = (List<?>) c; 
      l.add(new Integer(1));
    }
    */
        // if we know the element type of the collection then cast it
        // restrictive: requires casting
        Collection<Integer> l = (Collection<Integer>) c;
        l.add(new Integer(1));
    }

    public static Object getCollectionGeneric(Collection<?> c) {
        if (c instanceof List<?>) { // list collections support get
            List<?> l = (List<?>) c;
            Object e = l.get(0);  // get is ok
            return e;
        }

        return null;
    }

    public static void main(String[] agrs) {
        //
    }
}
