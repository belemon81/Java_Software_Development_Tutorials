package lects.lect05.meta;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

public class FieldSpy {
    public boolean[][] b = {{false, false}, {true, true}};
    public String name = "Alice";
    public List<Integer> list;
    public Object val;

    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName("lects.lect05.app.Course");
            Field f = c.getDeclaredField("name");
            System.out.format("Type: %s%n", f.getType());
            System.out.format("Annotations: %n");

            Annotation[] annotations = f.getAnnotations();
            if (annotations != null) {
                for (Annotation a : annotations) {
                    System.out.format("  %s%n", a);
                }
            }

            // production code should handle these exceptions more gracefully
        } catch (ClassNotFoundException | NoSuchFieldException x) {
            x.printStackTrace();
        }
    }
}