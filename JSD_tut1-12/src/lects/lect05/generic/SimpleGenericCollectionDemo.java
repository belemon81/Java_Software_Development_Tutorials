package lects.lect05.generic;

public class SimpleGenericCollectionDemo {
    public static void main(String[] agrs) {
        SimpleGenericCollection<Integer> sc = new SimpleGenericCollection();
        sc.add(1);
        sc.add(new Integer(1));

        sc.print();
    }
}
