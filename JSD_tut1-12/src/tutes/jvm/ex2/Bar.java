package tutes.jvm.ex2;

import tutes.jvm.ex2.baz.Baz;

public class Bar {
    public Bar(String a, String b) {
        System.out.println("bar! " + a + " " + b);
        new Baz(a, b);
        try {
            Class booClass = Class.forName("Boo");
            Object boo = booClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
