package tutes.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @overview defines a domain constraint of some attribute
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DomainConstraint {
    Type type() default Type.Null;

    boolean mutable() default false;

    boolean optional() default true;

    int length() default -1;

    double min() default Double.NEGATIVE_INFINITY;

    double max() default Double.POSITIVE_INFINITY;

    enum Type {
        String,
        Char,
        Integer,
        Long,
        Float,
        Double,
        Object,
        UserDefined, // domain specific type
        Null;

        public boolean isNumeric() {
            String n = this.name();
            return (n.equals(Type.Integer.name()) ||
                    n.equals(Type.Long.name()) ||
                    n.equals(Type.Float.name()) ||
                    n.equals(Type.Double.name())
            );
        }
    }
}
