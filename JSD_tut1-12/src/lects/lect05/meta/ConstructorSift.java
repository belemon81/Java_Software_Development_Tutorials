package lects.lect05.meta;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

import static java.lang.System.out;

public class ConstructorSift {
    public static void main(String... args) {
        try {
            Class<?> c = Class.forName("lects.lect05.app.Course");
            Class<?> cArg = Class.forName("java.lang.String");
            // find constructors of class c one of the parameters of which has the type cArg
            Constructor<?>[] allConstructors = c.getDeclaredConstructors();
            for (Constructor<?> ctor : allConstructors) {
                Class<?>[] pType = ctor.getParameterTypes();
                for (Class<?> aClass : pType) {
                    if (aClass.equals(cArg)) {
                        out.format("%s%n", ctor.toGenericString());
                        Type[] gpType = ctor.getGenericParameterTypes();
                        for (int j = 0; j < gpType.length; j++) {
                            char ch = (pType[j].equals(cArg) ? '*' : ' ');
                            out.format("%7c%s[%d]: %s%n", ch, "GenericParameterType", j,
                                    gpType[j]);
                        }
                        break;
                    }
                }
            }

            // production code should handle this exception more gracefully
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }
}
