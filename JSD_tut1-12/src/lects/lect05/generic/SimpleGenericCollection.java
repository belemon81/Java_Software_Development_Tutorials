package lects.lect05.generic;

import java.util.ArrayList;
import java.util.List;

public class SimpleGenericCollection<E> extends SimpleGeneric {
    // use a generic List to store elements of this collection
    private List<E> list;

    public SimpleGenericCollection() {
        super();
        list = new ArrayList<E>();
    }

    // add an element to list
    public void add(E x) {
        list.add(x);
    }

    // print this collection
    public void print() {
        for (Object x : list) {
            super.print(x);
        }
    }
}
