package lects.lect05.meta;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static java.lang.System.out;

class A {

    A() {
        out.println("A.init()");
    }

    public A(String s) {
        out.println("A.init(" + s + ")");
    }

    public String toString() {
        return "A";
    }
}

public class ConstructorDemo {

    public static void main(String[] args) {

        Constructor c;
        A a;
        try {
            c = A.class.getDeclaredConstructor(null);
            a = (A) c.newInstance();

            c = A.class.getConstructor(String.class);
            a = (A) c.newInstance("Hello world");
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
