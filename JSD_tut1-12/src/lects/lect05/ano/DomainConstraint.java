package lects.lect05.ano;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @overview Defines a domain constraint of some attribute
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DomainConstraint {
    /**
     * the data type, e.g. String, Integer, Long, Float, Double
     */
    public String type();

    /**
     * whether this field is mutable
     */
    public boolean mutable() default false;

    /**
     * whether this field is optional
     */
    public boolean optional() default true;

    /**
     * the maximum length of this field (applied to String-type only, default: -1)
     */
    public int length() default -1;

    /**
     * the min value of this field (applied to number types only, default: <tt>Double.NaN</tt>
     */
    public double min() default Double.NaN;

    /**
     * the max value of this field (applied to number types only, default: <tt>Double.NaN</tt>
     */
    public double max() default Double.NaN;
}
