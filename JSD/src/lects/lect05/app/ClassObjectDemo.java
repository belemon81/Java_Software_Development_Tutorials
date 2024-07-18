package lects.lect05.app;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassObjectDemo {
    public static void main(String[] args)
            throws ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException,
            IllegalAccessException,
            InstantiationException {
        Class cls = Class.forName("lects.lect05.app.Course");
        Constructor c = cls.getConstructors()[0];
        Object obj = c.newInstance(15, "PR1");
        System.out.println(obj);
        Method m = cls.getMethod("setName", String.class);
        m.invoke(obj, "PR2");
        System.out.println(obj);
    }
}
