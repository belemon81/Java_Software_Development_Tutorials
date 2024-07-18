package lects.lect05.meta;

import static java.lang.System.out;

public class GetClassDemo {
    public static void getClasses(Class c) {
        out.println("Class " +
                "\n  " + c.getName());
        out.println("Super class: " +
                "\n  " + c.getSuperclass());

        out.println("Classes: ");
        Class<Class>[] cls = c.getClasses();
        for (Class cl : cls) {
            out.println("  " + cl.getName());
        }

        out.println("Declared classes: ");
        cls = c.getDeclaredClasses();
        for (Class cl : cls) {
            out.println("  " + cl.getName());
        }

        out.println("Enclosing class: " +
                "\n  " + c.getEnclosingClass());
    }

    public static void main(String[] args) {
        // getClass

        Class c = "foo".getClass();
        out.println(c.getName());

        c = System.out.getClass();
        out.println(c.getName());

        c = E.A.getClass();
        out.println(c.getName());

        byte[] bytes = new byte[1024];
        c = bytes.getClass();
        out.println(c.getName());

        // .class
        boolean b = true;
        // compile-time error
        // c = b.getClass();

        c = boolean.class; // correct
        out.println(c.getName());

        c = int[][][].class;
        out.println(c.getName());

        c = java.io.PrintStream.class;
        out.println(c.getName());

        // forName
        try {
            c = Class.forName("beanmeta.urs.Course");
            c = Class.forName("[D"); // double[].class
            c = Class.forName("[[I"); // int[][].class
            c = Class.forName("[[Ljava.lang.String;"); // String[][].class
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // get classes
        getClasses(GetClassDemo.class);

        getClasses(GetClassDemo.Inner.class);
    }

    public enum E {
        A, B
    }

    class Inner {
    }
}
