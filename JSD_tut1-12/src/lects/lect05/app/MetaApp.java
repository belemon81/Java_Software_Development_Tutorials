package lects.lect05.app;

import lects.lect05.ano.DomainConstraint;
import lects.lect05.ano.NotPossibleException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * this demonstrates the use of DomainConstraint to validate data input
 * of a class without needing to invoke its constructor method
 */
public class MetaApp {
    /**
     * The domain constraint class object
     */
    private static final Class<DomainConstraint> dcls = DomainConstraint.class;

    public static void main(String[] args) {
        // a given class
        Class<Course> cls = Course.class;

        // some test input data for the given class
        // ASSUME: order of data values is the same as order of fields
        Object[] data = {
                1, // "1a",// 1, //123,
                "Java Technology", //"abcdefghijklmnop", //null, //"Software Engineering",
        };

        // get the fields
        List<Field> attributes = getAttributes(cls);

        // validate data values against field's domain constraints
        boolean validate = true;
        String name;
        Object value, vali;
        DomainConstraint dc;
        int index = 0;
        for (Field f : attributes) {
            name = f.getName();
            dc = f.getAnnotation(DomainConstraint.class);
            value = data[index++];
            try {
                vali = validate(value, dc);
                System.out.println("Attribute: " + name + "\n\tvalue: " + value
                        + " -> " + vali + " (" + vali.getClass() + ")");
            } catch (NotPossibleException e) {
                validate = false;
                System.err.println("Attribute: " + name + "\n\tvalue: " + value
                        + " -> " + e.getMessage());
            }
        }

        // if we get here with validate = true,
        // then data values are valid, create an object of the class
        // using the data
        if (validate) {
            try {
                Constructor<Course> cons = cls.getConstructor(int.class, String.class);
                Course o = cons.newInstance(data);
                // do something with this object
                System.out.println("Created object: " + o);
            } catch (Exception e) {
                System.err.println("Could not create new object of " + cls);
                e.printStackTrace();
            }
        }
    }

    /**
     * Return the attributes of a class that are annotated with
     * <tt>DomainConstraint</tt>.
     *
     * @requires <tt>c != null && c </tt> contains fields annotated with
     * <tt>DomainConstraint</tt>.
     * @effects returns the attributes annotated with <tt>DomainConstraint</tt>.
     * Each attribute is represented by a <code>Field</code> object
     */
    public static List<Field> getAttributes(final Class<?> c) {
        Field[] myFields = c.getDeclaredFields();
        List<Field> _fields = new ArrayList<>();
        Collections.addAll(_fields, myFields);

        Class<?> superClass = c.getSuperclass();
        if (!superClass.equals(Object.class)) {
            List<Field> parentFields = getAttributes(superClass);
            _fields.addAll(parentFields);
        }

        List<Field> fields = new ArrayList<>();
        Field f;
        for (Field field : _fields) {
            f = field;
            if (f.getAnnotation(dcls) != null) {
                fields.add(f);
            }
        } // end for loop

        return fields;
    }

    /**
     * Validate a data value against a given domain constraint.
     *
     * @effects if <tt>value</tt> satisfies <tt>dc</tt> returns the validated
     * object
     * else throws <tt>NotPossibleException</tt>
     */
    public static Object validate(Object value, DomainConstraint dc)
            throws NotPossibleException {
        Object val = null;

        // validate object against the domain constraint
        // validate optional constraint
        if (!dc.optional() && value == null) {
            throw new NotPossibleException("validate: value cannot be null");
        }

        String type = dc.type();
        if (type.equals("String")) {
            if (dc.length() > 0) {
                val = value.toString();
                // validate length constraint
                if (((String) val).length() > dc.length())
                    throw new NotPossibleException(
                            "validate: value has incorrect length: " + val);
            }
        }

        if (type.equals("Integer") || type.equals("Long") || type.equals("Float")
                || type.equals("Double")) {
            // convert value to number
            if (!(value instanceof Number)) {
                try {
                    switch (type) {
                        case "Integer":
                            val = Integer.parseInt(value.toString());
                            break;
                        case "Long":
                            val = Long.parseLong(value.toString());
                            break;
                        case "Float":
                            val = Float.parseFloat(value.toString());
                            break;
                        case "Double":
                            val = Double.parseDouble(value.toString());
                            break;
                    }
                } catch (NumberFormatException e) {
                    throw new NotPossibleException(
                            "validate: cannot convert value to type " + type + " :" + value);
                }
            } else { // already a number
                val = value;
            }

            // validate min and max constraints
            if (!Double.isNaN(dc.min())) {
                if (((Number) val).doubleValue() < dc.min())
                    throw new NotPossibleException("validate: value smaller than min: "
                            + value);
            }

            if (!Double.isNaN(dc.max())) {
                if (((Number) val).doubleValue() > dc.max())
                    throw new NotPossibleException("validate: value larger than max: "
                            + value);
            }
        }
        // add support for other types here...
        else {
            val = value;
        }

        // if we get here then ok
        return val;
    }
}
